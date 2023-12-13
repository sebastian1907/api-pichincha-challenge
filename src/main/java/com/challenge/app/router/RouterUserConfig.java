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
public class RouterUserConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(UserHandler userHandler) {
        return route(POST("public/api/user"), userHandler::crear)
                .andRoute(POST("api/login"), userHandler::login);
    }
}
