package com.emphasoft.demo.service;

import com.emphasoft.demo.dto.QueryExchangeDto;
import com.emphasoft.demo.dto.ResponseExchangeDto;
import com.emphasoft.demo.enums.Currency;
import com.emphasoft.demo.model.Exchange;
import com.emphasoft.demo.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;
    private final RestTemplate restTemplate;

    public ResponseExchangeDto exchangeSaveAndGet(QueryExchangeDto queryExchangeDto) {
        Exchange exchange = createExchangeFromDto(queryExchangeDto);
        ResponseExchangeDto resultDto = convertExchangeToDto(exchange.getId(), getNewCurrencyAmount(exchange));
        exchange.setNewCurrencyAmount(resultDto.getNewCurrencyAmount());
        exchangeRepository.save(exchange);
        resultDto.setExchangeId(exchange.getId());
        return resultDto;
    }

    public Exchange createExchangeFromDto(QueryExchangeDto queryExchangeDto) {
        return Exchange.builder()
                .amount(queryExchangeDto.getAmount())
                .from(Currency.valueOf(queryExchangeDto.getFrom().toUpperCase()))
                .to(Currency.valueOf(queryExchangeDto.getTo().toUpperCase()))
                .userId(queryExchangeDto.getUserId())
                .build();
    }

    public BigDecimal getNewCurrencyAmount(Exchange exchange) {
        String uri = String.format("https://free.currconv.com/api/v7/convert?q=%s_%s&compact=ultra&apiKey=13471b2baae61d8d3020",
                exchange.getFrom().name(), exchange.getTo().name());
        String jsonResponse = restTemplate.getForObject(uri, String.class);
        String result = jsonResponse.split(":")[1].replace("}", "");
        return BigDecimal.valueOf(Double.parseDouble(result) * exchange.getAmount());
    }

    public ResponseExchangeDto convertExchangeToDto(Long exchangeId, BigDecimal newCurrencyAmount) {
        return new ResponseExchangeDto(exchangeId, newCurrencyAmount);
    }
}
