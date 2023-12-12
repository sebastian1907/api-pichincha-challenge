package com.challenge.app.service.impl;

import com.challenge.app.entity.User;
import com.challenge.app.repository.UserRepository;
import com.challenge.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    /*public Mono<User> create(User user) {
        return userRepository.save(user.toBuilder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .roles(Collections.singletonList("ROLE_USER"))
                        .enabled(Boolean.TRUE)
                        .createdAt(LocalDateTime.now())
                        .build())
                .doOnSuccess(u -> log.info("Created new user with ID = " + u.getId()));
    }*/

    public Mono<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }
}
