package com.ostapenko.cryptocurrency.repository;

import com.ostapenko.cryptocurrency.entity.Crypto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimerRepository extends MongoRepository<Crypto, String> {

    Crypto sortCurrencyByLprice(String name);

    Crypto sortByDecsCurrencyByLprice(String name);

    Page<Crypto> findByCurr1(String name, Pageable pageable);
}
