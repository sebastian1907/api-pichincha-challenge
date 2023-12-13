package com.challenge.app.repository;

import com.challenge.app.entity.MoneyExchange;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MoneyExchangeRepository extends ReactiveMongoRepository<MoneyExchange, String> {
}
