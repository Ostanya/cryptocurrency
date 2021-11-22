package com.ostapenko.cryptocurrency.service;

import com.ostapenko.cryptocurrency.entity.Crypto;
import com.ostapenko.cryptocurrency.repository.TimerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //generates a constructor with 1 parameter for each field that requires special handling
public class CryptoCurrService {
    private TimerRepository timerRepository;

    @Autowired
    public CryptoCurrService(TimerRepository timerRepository) {
        this.timerRepository = timerRepository;
    }

    public double getMinPrice(String name) {
        Crypto crypto = timerRepository.sortCurrencyByLprice(name);
        return crypto.getLprice();
    }

    public double getMaxPrice(String name) {
        Crypto crypto = timerRepository.sortByDecsCurrencyByLprice(name);
        return crypto.getLprice();
    }

    public Page<Crypto> getCrypto(String name, Pageable pageable) {
        return timerRepository.findByCurr1(name, pageable);
    }
}
