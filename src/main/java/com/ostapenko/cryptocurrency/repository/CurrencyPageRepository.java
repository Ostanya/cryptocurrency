package com.ostapenko.cryptocurrency.repository;

import com.ostapenko.cryptocurrency.entity.Crypto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyPageRepository extends PagingAndSortingRepository<Crypto, Integer> {
}
