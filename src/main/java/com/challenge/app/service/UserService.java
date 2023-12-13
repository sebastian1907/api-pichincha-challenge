package com.challenge.app.service;

import com.challenge.app.entity.User;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> createUser(User user);
    Mono<User> getUser(String userId);
}
