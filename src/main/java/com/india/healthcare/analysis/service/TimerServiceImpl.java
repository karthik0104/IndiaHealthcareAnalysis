package com.india.healthcare.analysis.service;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.india.healthcare.analysis.Application;

public class TimerServiceImpl implements TimerService {
	
	private Timer timer;
	private RestTemplate restTemplate;
	
	public void callServiceAfterFixedInterval(final String url, Long interval) {
		final Application a = new Application();
		timer = new Timer();
    	restTemplate = new RestTemplate();
    	timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				ResponseEntity<String> response = restTemplate.getForEntity(
						url, String.class);
			}
    		
    	}, 0, interval);
	}

}
