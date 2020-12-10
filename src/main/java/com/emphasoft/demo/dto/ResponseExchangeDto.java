package com.emphasoft.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseExchangeDto {

    private Long exchangeId;

    private BigDecimal newCurrencyAmount;

    @Override
    public String toString() {
        return "{" +
                "exchangeId=" + exchangeId +
                ", newCurrencyAmount=" + newCurrencyAmount +
                '}';
    }
}
