package com.ostapenko.cryptocurrency.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ostapenko.cryptocurrency.entity.Crypto;
import com.ostapenko.cryptocurrency.repository.TimerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.TimerTask;

public class TimerService extends TimerTask {
    private static final String uri = "https://cex.io/api/last_price/";
    public static final String BTC = "BTC";
    public static final String USD = "USD";
    public static final String ETH = "ETH";
    public static final String XPR = "XPR";

    @Override
    public void run() {
        try {
            update(BTC, USD);
            update(ETH, USD);
            update(XPR, USD);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private TimerRepository timerRepository;

    public Crypto update(String curr1, String curr2) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri + curr1 + "/" + curr2, String.class);
        System.out.println(result);

        Crypto value = new ObjectMapper().readValue(result, Crypto.class);
        System.out.println(value);
        return timerRepository.save(value);
    }
}
