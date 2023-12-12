package com.challenge.app.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExchangeDto {

    @NotNull
    private Double amount;
    @NotEmpty
    private String origenCurrency;
    @NotEmpty
    private String destinyCurrency;
    private Double amountExchange;
    private Double exchangeRate;
}
