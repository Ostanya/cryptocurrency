package com.ostapenko.cryptocurrency.controller;

import com.ostapenko.cryptocurrency.entity.Crypto;
import com.ostapenko.cryptocurrency.service.CryptoCurrService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static com.ostapenko.cryptocurrency.service.TimerService.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cryptocurrencies")
public class CryptoController {
    private static List<String> list = Arrays.asList(BTC, XPR, ETH);

    private CryptoCurrService cryptoCurrService;

    @Autowired
    public CryptoController(CryptoCurrService cryptoCurrService) {
        this.cryptoCurrService = cryptoCurrService;
    }

    @GetMapping(value = "/minprice")
    public double getMinPrice(@RequestParam String name) {
        if(list.contains(name)) {
            throw new RuntimeException("Wrong request parameters");
        }
        double minPrice = cryptoCurrService.getMinPrice(name);
       // log.info("Find min price");
        return minPrice;
    }

    @GetMapping(value = "/maxprice")
    public double getMaxPrice(@RequestParam String name) {
        if(list.contains(name)) {
            throw new RuntimeException("Wrong request param");
        }
        double maxPrice = cryptoCurrService.getMaxPrice(name);
        //log.info("Find max price");
        return maxPrice;
    }

    @GetMapping
    public Page<Crypto> getNameOfCryptocurrencies(@RequestParam String name,
                                                  @RequestParam int page,
                                                  @RequestParam int size) {
        if(page < 0 || size <= 0 || list.contains(name)) {
            throw new RuntimeException("Wrong request parameters");
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("lprice"));
       // log.info("Find list of {} cryptocurrencies records", name);
        return cryptoCurrService.getCrypto(name, pageable);
    }
}
