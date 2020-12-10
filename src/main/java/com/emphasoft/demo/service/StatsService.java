package com.emphasoft.demo.service;

import com.emphasoft.demo.dto.UserWithTotalExchangesMoreThanDto;
import com.emphasoft.demo.model.Exchange;
import com.emphasoft.demo.model.User;
import com.emphasoft.demo.repository.ExchangeRepository;
import com.emphasoft.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final ExchangeRepository exchangeRepository;
    private final UserRepository userRepository;

    public ResponseEntity<?> getUsersExchangingFromAndMoreThan(String exchange_from, Integer amount) {
        if (amount < 0) {
            return ResponseEntity.badRequest().body("Введите положительное целое число.");
        }
        List<User> resultList = new ArrayList<>();
        for (Long id : Objects.requireNonNull(getUserIdsFromExchangesRepo(exchange_from, amount).getBody())) {
            resultList.addAll(userRepository.findById(id).stream().collect(Collectors.toList()));
        }
        return ResponseEntity.ok(resultList);
    }

    public ResponseEntity<?> getUsersWithTotalExchangesMoreThan(String exchange_from, Integer amount) {
        if (amount < 0) {
            return ResponseEntity.badRequest().body("Введите положительное целое число.");
        }
        List<UserWithTotalExchangesMoreThanDto> resultList = new ArrayList<>();
        List<User> allUsersWithAnyExchanges = userRepository.findAllById(getUserIdsFromExchangesRepo(exchange_from, 0).getBody());
        for (User currentUser : allUsersWithAnyExchanges) {
            int sumCalculator = 0;
            List<Integer> currentUserExchanges = exchangeRepository.getAmountsByUserId(currentUser.getId());
            for (Integer i : currentUserExchanges) {
                sumCalculator += i;
            }
            if (sumCalculator > amount) {
                resultList.add(UserWithTotalExchangesMoreThanDto.builder()
                        .id(currentUser.getId())
                        .login(currentUser.getLogin())
                        .exchangeAmountList(currentUserExchanges)
                        .build());
            }
        }
        return ResponseEntity.ok(resultList);
    }

    public ResponseEntity<Set<Long>> getUserIdsFromExchangesRepo(String exchange_from, Integer amount) {
        return ResponseEntity.ok(
                exchangeRepository.getUsersExchangingMoreThan(exchange_from, amount).stream()
                        .map(Exchange::getUserId)
                        .collect(Collectors.toSet())
        );
    }

    public ResponseEntity<?> getExchangesByPopularity() {
        Map<String, Integer> exchangesAndStats = new HashMap<>();
        for (Exchange e : exchangeRepository.findAll()) {
            exchangesAndStats.computeIfPresent(e.getFrom().name() + " to " + e.getTo().name(), (k, v) -> ++v);
            exchangesAndStats.putIfAbsent(e.getFrom().name() + " to " + e.getTo().name(), 1);
        }
        return ResponseEntity.ok(exchangesAndStats.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())));
    }
}
