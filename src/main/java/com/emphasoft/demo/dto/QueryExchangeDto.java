package com.emphasoft.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryExchangeDto {

    @NotNull
    private Long userId;

    @Positive
    private Integer amount;

    @Size(min = 3, max = 3)
    private String from;

    @Size(min = 3, max = 3)
    private String to;

    @Override
    public String toString() {
        return "{" +
                "userId=" + userId +
                ", amount=" + amount +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
