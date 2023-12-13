package com.challenge.app.service;

import com.challenge.app.entity.Exchange;
import com.challenge.app.entity.MoneyExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExchangeService {

    Mono<Exchange> save(Exchange exchange);
    Mono<Exchange> findByCurrencies(String origenCurrency, String destinyCurrency);
    Flux<Exchange> findAll();
    Mono<Exchange> findById(String id);
    Mono<MoneyExchange> saveMoneyExchange(MoneyExchange moneyExchange);
}
