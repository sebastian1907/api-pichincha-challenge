package com.challenge.app.router;

import com.challenge.app.handler.ExchangeHandler;
import com.challenge.app.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterExchangeConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(ExchangeHandler exchangeHandler, UserHandler userHandler) {
        return route(POST("/api/exchange"), exchangeHandler::crear)
                .andRoute(GET("/api/exchange"), exchangeHandler::listar)
                .andRoute(GET("/api/exchange/{origenCurrency}/{destinyCurrency}"), exchangeHandler::ver)
                .andRoute(PUT("/api/exchange/{id}"), exchangeHandler::editar)
                .andRoute(POST("/api/exchange/amount"), exchangeHandler::cambiar)
                .andRoute(POST("/public/api/user"), userHandler::crear)
                .andRoute(POST("/public/api/login"), userHandler::login);
    }
}
