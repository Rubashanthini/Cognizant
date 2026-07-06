package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> getFacebookStocksInSeptember2019() {
        LocalDate start = LocalDate.of(2019, 9, 1);
        LocalDate end = LocalDate.of(2019, 9, 30);
        return stockRepository.findByStockSymbolAndTradeDateBetween("FB", start, end);
    }

    public List<Stock> getGoogleStocksAboveClosePrice(Double price) {
        return stockRepository.findByStockSymbolAndClosePriceGreaterThan("GOOGL", price);
    }

    public List<Stock> getTop3HighestVolumeStocks() {
        return stockRepository.findTop3ByOrderByVolumeDesc();
    }

    public List<Stock> getLowestThreeNetflixPrices() {
        return stockRepository.findTop3ByStockSymbolOrderByClosePriceAsc("NFLX");
    }
}
