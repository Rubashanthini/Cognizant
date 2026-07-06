package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    // HANDS-ON 2: Facebook stocks during September 2019
    List<Stock> findByStockSymbolAndTradeDateBetween(String stockSymbol, LocalDate startDate, LocalDate endDate);

    // HANDS-ON 2: Google stocks where closing price > 1250
    List<Stock> findByStockSymbolAndClosePriceGreaterThan(String stockSymbol, Double closePrice);

    // HANDS-ON 2: Top 3 highest transaction volumes
    List<Stock> findTop3ByOrderByVolumeDesc();

    // HANDS-ON 2: Lowest three Netflix stock prices
    List<Stock> findTop3ByStockSymbolOrderByClosePriceAsc(String stockSymbol);
}
