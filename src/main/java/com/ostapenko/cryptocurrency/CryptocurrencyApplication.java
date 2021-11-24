package com.ostapenko.cryptocurrency;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ostapenko.cryptocurrency.entity.Crypto;
import com.ostapenko.cryptocurrency.service.CryptoCurrService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootApplication
public class CryptocurrencyApplication {

	CryptoCurrService service;

	public static void main(String[] args) {

		SpringApplication.run(CryptocurrencyApplication.class, args);

	}

	@Bean
	@Scheduled(cron = "*/10 * * * *")
	public void loader() {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Crypto>> typeReference = new TypeReference<>() {};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/json/currencyPair.json");
		try {
			List<Crypto> currencies =
					mapper.readValue(inputStream, typeReference)
							.stream().limit(10).collect(Collectors.toList());
			service.save(currencies);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
