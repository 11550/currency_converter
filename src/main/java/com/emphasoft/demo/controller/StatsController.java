package com.emphasoft.demo.controller;

import com.emphasoft.demo.service.StatsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stats")
@RequiredArgsConstructor
@Slf4j
public class StatsController {

    private final StatsService statsService;

    @GetMapping("by_one_time")
    ResponseEntity<?> getUsersExchangingMoreThan(@RequestParam(defaultValue = "USD") String exchange_from,
                                                 @RequestParam Integer amount) {
        log.debug(String.format("\"getUsersExchangingMoreThan\" is init. Input:%s,%s", exchange_from, amount));
        return ResponseEntity.ok(statsService.getUsersExchangingFromAndMoreThan(exchange_from, amount));
    }

    @GetMapping("by_all_times")
    ResponseEntity<?> getUsersWithTotalCashFlowMoreThan(@RequestParam(defaultValue = "USD") String exchange_from,
                                                        @RequestParam Integer amount) {
        log.debug(String.format("\"getUsersWithTotalCashFlowMoreThan\" is init. Input:%s,%s", exchange_from, amount));
        return ResponseEntity.ok(statsService.getUsersWithTotalExchangesMoreThan(exchange_from, amount));
    }

    @GetMapping
    ResponseEntity<?> getExchangingStats() {
        log.debug("\"getExchangingStats\" is init.");
        return ResponseEntity.ok(statsService.getExchangesByPopularity());
    }
}
