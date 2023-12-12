package com.challenge.app.service;

import com.challenge.app.entity.User;
import reactor.core.publisher.Mono;

public interface UserService {

    //public Mono<User> create(User user);
    public Mono<User> getUser(Long userId);
}
