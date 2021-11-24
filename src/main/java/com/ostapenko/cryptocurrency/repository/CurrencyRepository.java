package com.ostapenko.cryptocurrency.repository;

import com.ostapenko.cryptocurrency.entity.Crypto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CurrencyRepository extends MongoRepository<Crypto, Integer> {
}
