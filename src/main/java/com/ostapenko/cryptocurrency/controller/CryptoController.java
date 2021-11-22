package com.ostapenko.cryptocurrency.controller;

import com.ostapenko.cryptocurrency.entity.Crypto;
import com.ostapenko.cryptocurrency.service.CryptoCurrService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

import static com.ostapenko.cryptocurrency.service.TimerService.*;

public class CryptoController {
    private static List<String> list = Arrays.asList(BTC, XPR, ETH);

    private CryptoCurrService cryptoCurrService;

    @GetMapping(value = "/minprice")
    public double getMinPrice(String name) {
        double minPrice = cryptoCurrService.g
        return minPrice;
    }

    public double getMaxPrice() {}

    @GetMapping
    public Page<Crypto> getNameOfCryptocurrencies(String name, int page, int size) {
        if(page < 0 || size <= 0 || list.contains(name)) {
            throw new RuntimeException("Wrong request parameters");
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("lprice"));
        log.info("Find list of {} cryptocurrencies records", name);
        return ;
    }
}
