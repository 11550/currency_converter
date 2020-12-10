package com.emphasoft.demo.repository;

import com.emphasoft.demo.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

    @Query(value = "SELECT * FROM Exchanges E WHERE (E.exchange_from = ?1 AND E.amount > ?2)", nativeQuery = true)
    List<Exchange> getUsersExchangingMoreThan(@Param("exchange_from") String exchange_from,
                                              @Param("amount") Integer amount);

    @Query(value = "SELECT E.amount From Exchanges E WHERE (E.user_id = ?1)", nativeQuery = true)
    List<Integer> getAmountsByUserId(@Param("user_id") Long userId);
}
