package com.ostapenko.cryptocurrency;

import com.ostapenko.cryptocurrency.service.TimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Timer;

@SpringBootApplication
public class CryptocurrencyApplication {
	@Autowired
	TimerService timerService;

	public static void main(String[] args) {

		SpringApplication.run(CryptocurrencyApplication.class, args);

	}

	@EventListener(ApplicationReadyEvent.class)
	public void startTimer() {
		Timer timer = new Timer();
		timer.schedule(timerService, 0, 10000);
	}
}
