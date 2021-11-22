package com.ostapenko.cryptocurrency.repository;

import com.ostapenko.cryptocurrency.entity.Crypto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimerRepository extends MongoRepository<Crypto, String> {

    Crypto sortCurrencyByLprice(String name);
}
