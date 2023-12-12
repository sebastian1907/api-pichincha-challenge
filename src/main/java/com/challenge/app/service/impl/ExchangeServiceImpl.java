package com.challenge.app.service.impl;

import com.challenge.app.entity.Exchange;
import com.challenge.app.repository.ExchangeRepository;
import com.challenge.app.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    ExchangeRepository exchangeRepository;

    @Override
    public Mono<Exchange> save(Exchange exchange){
        return exchangeRepository.save(exchange);
    }

    @Override
    public Mono<Exchange> findByCurrencies(String origenCurrency, String destinyCurrency) {
        return exchangeRepository.findByOrigenCurrencyAndDestinyCurrency(origenCurrency, destinyCurrency);
    }

    @Override
    public Flux<Exchange> findAll() {
        return exchangeRepository.findAll();
    }

     @Override
    public Mono<Exchange> findById(String id) {
        return exchangeRepository.findById(id);
     }

}
