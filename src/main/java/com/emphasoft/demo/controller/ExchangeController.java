package com.emphasoft.demo.controller;

import com.emphasoft.demo.dto.QueryExchangeDto;
import com.emphasoft.demo.dto.ResponseExchangeDto;
import com.emphasoft.demo.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("exchange")
@RequiredArgsConstructor
@Slf4j
public class ExchangeController {

    private final ExchangeService exchangeService;

    @PostMapping
    public ResponseEntity<ResponseExchangeDto> exchangeAndGet(@RequestBody @Valid QueryExchangeDto queryExchangeDto) {
        log.debug(String.format("Exchange is init. Input:%s", queryExchangeDto.toString()));
        ResponseExchangeDto result = exchangeService.exchangeSaveAndGet(queryExchangeDto);
        log.debug(String.format("Exchange is done. Output:%s", result.toString()));
        return ResponseEntity.ok(result);
    }
}
