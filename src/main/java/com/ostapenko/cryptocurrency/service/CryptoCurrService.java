package com.ostapenko.cryptocurrency.service;

import org.springframework.data.mongodb.core.query.Criteria;
import com.ostapenko.cryptocurrency.entity.Crypto;
import com.ostapenko.cryptocurrency.exception.NoMatchesException;
import com.ostapenko.cryptocurrency.repository.CurrencyPageRepository;
import com.ostapenko.cryptocurrency.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import org.springframework.data.mongodb.core.query.Query;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Service
public class CryptoCurrService {
    @Autowired
    private CurrencyRepository repository;

    @Autowired
    private CurrencyPageRepository pageRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Crypto> save(List<Crypto> currency) {
        return repository.saveAll(currency);
    }

    public Crypto findLpriceByName(String name) {
        if(repository.findAll().stream().noneMatch(Predicate.isEqual(name))) {
            throw new NoMatchesException("Currency with name: " + name + " doesn't exist");
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name).
                and("price").is(repository.findAll().
                        stream().min(Comparator.comparing(Crypto::getMinPrice))));

        return mongoTemplate.findOne(query, Crypto.class);
    }

    public Crypto findHpriceByName(String name) {
        if(repository.findAll().stream().noneMatch(Predicate.isEqual(name))) {
            throw new NoMatchesException("Currency with name: " + name + " doesn't exist");
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name).
                and("price").is(repository.findAll().
                        stream().max(Comparator.comparing(Crypto::getMaxPrice))));

        return mongoTemplate.findOne(query, Crypto.class);
    }

    public List<Crypto> findSelectedPageWithSelectedNumbers(int page, int size) {
        return pageRepository.findAll(PageRequest.of(page, size)).
                stream().sorted(Comparator.comparingDouble(Crypto::getMinPrice)).
                collect(Collectors.toList());
    }

    public void exportToCSV(HttpServletResponse response) {
        String csv = "csv";
        response.setContentType(csv);
        String headerKey = "headerKey";
        String headerValue = "export.csv";
        response.setHeader(headerKey, headerValue);

        try {
            ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

            String[] currencyExport = {"name", "min_price", "max_price"};

            for(Crypto currency : repository.findAll()) {
                csvWriter.write(currency, currencyExport);
            }
        } catch (IOException exception) {
            exception.getStackTrace();
        }
    }
}
