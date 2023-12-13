package com.challenge.app.handler;

import com.challenge.app.dto.ExchangeDto;
import com.challenge.app.entity.Exchange;
import com.challenge.app.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Date;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
public class ExchangeHandler {

    @Autowired
    ExchangeService exchangeService;

    //@Autowired
    //JWTServiceImpl jwtServiceI;

    @Autowired
    Validator validator;

    public Mono<ServerResponse> crear(ServerRequest serverRequest) {

        return serverRequest.bodyToMono(Exchange.class).flatMap(e -> {
            Errors errors = new BeanPropertyBindingResult(e, Exchange.class.getName());
            validator.validate(e, errors);
            if (errors.hasErrors()){
                return Flux.fromIterable(errors.getFieldErrors())
                        .map(fieldError -> "El campo "+fieldError.getField()+" "+fieldError.getDefaultMessage())
                        .collectList()
                        .flatMap(list -> ServerResponse.badRequest().body(fromValue(list)));
            } else {
                e.setCreatedAt(new Date());
                return exchangeService.save(e).flatMap(edb -> ServerResponse.created(URI.create("/api/exchange/".concat(edb.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(edb)));
            }

        });

    }

    public Mono<ServerResponse> listar(ServerRequest serverRequest){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(exchangeService.findAll(), Exchange.class);
    }

    public Mono<ServerResponse> ver(ServerRequest serverRequest) {

        return exchangeService
                .findByCurrencies(serverRequest.pathVariable("origenCurrency"),
                        serverRequest.pathVariable("destinyCurrency"))
                .flatMap(p -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(p)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> editar(ServerRequest serverRequest) {
        return exchangeService.findById(serverRequest.pathVariable("id"))
                .zipWith(serverRequest.bodyToMono(Exchange.class), (db, req) -> {
                    db.setExchangeRate(req.getExchangeRate());
                    return db;
                }).flatMap(e -> ServerResponse.created(URI.create("/api/exchange/".concat(e.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(exchangeService.save(e), Exchange.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> cambiar(ServerRequest serverRequest) {

        return serverRequest.bodyToMono(ExchangeDto.class).flatMap(exchangeDto -> {
            Errors errors = new BeanPropertyBindingResult(exchangeDto, Exchange.class.getName());
            validator.validate(exchangeDto, errors);
            if (errors.hasErrors()){
                return Flux.fromIterable(errors.getFieldErrors())
                        .map(fieldError -> "El campo "+fieldError.getField()+" "+fieldError.getDefaultMessage())
                        .collectList()
                        .flatMap(list -> ServerResponse.badRequest().body(fromValue(list)));
            } else {

                return exchangeService.findByCurrencies(exchangeDto.getOrigenCurrency(), exchangeDto.getDestinyCurrency())
                        .flatMap(exchange -> {
                            exchangeDto.setExchangeRate(exchange.getExchangeRate());
                            exchangeDto.setAmountExchange(exchangeDto.getAmount() * exchangeDto.getExchangeRate());

                            return Mono.just(exchangeDto);
                        }).flatMap( e -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(fromValue(e)))
                        .switchIfEmpty(ServerResponse.notFound().build());
            }

        });

    }
}
