package com.ostapenko.cryptocurrency.controller;

import com.ostapenko.cryptocurrency.entity.Crypto;
import com.ostapenko.cryptocurrency.service.CryptoCurrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
public class CryptoController {
    @Autowired
    private CryptoCurrService service;

    @GetMapping("/cryptocurrencies/minprice")
    public Crypto minCurrency(@RequestParam String name) {
        return service.findLpriceByName(name);
    }

    @GetMapping("/cryptocurrencies/maxprice")
    public Crypto maxCurrency(@RequestParam String name) {
        return service.findHpriceByName(name);
    }

    @GetMapping("/cryptocurrencies")
    public List<Crypto> findCurrenciesByPageAndSize (@RequestParam(required = false, defaultValue = "0") int page,
                                                     @RequestParam(required = false, defaultValue = "10") int size) {
        return service.findSelectedPageWithSelectedNumbers(page, size);
    }

    @GetMapping("/cryptocurrencies/csv")
    public void exportToCsv(HttpServletResponse response) {
        service.exportToCSV(response);
    }
}
