package com.emphasoft.demo.model;

import com.emphasoft.demo.enums.Currency;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@Table(name = "Exchanges")
@AllArgsConstructor
@NoArgsConstructor
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "exchange_from")
    @Enumerated(EnumType.STRING)
    private Currency from;

    @Column(name = "exchange_to")
    @Enumerated(EnumType.STRING)
    private Currency to;

    @Column(name = "new_currency_amount")
    private BigDecimal newCurrencyAmount;

    @Column(name = "user_id")
    private Long userId;
}
