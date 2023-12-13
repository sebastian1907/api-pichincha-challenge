package com.challenge.app.service;

import com.challenge.app.dto.auth.TokenInfo;
import com.challenge.app.entity.User;
import reactor.core.publisher.Mono;

public interface JWTService {

    TokenInfo generateAccessToken(User user);
    Mono<TokenInfo> authenticate(String username, String password);
}
