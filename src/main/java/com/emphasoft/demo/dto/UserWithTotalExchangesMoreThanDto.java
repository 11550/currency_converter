package com.emphasoft.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserWithTotalExchangesMoreThanDto {

    private Long id;

    private String login;

    private List<Integer> exchangeAmountList;
}
