package com.india.healthcare.analysis.service;

public interface TimerService {
	
	public void callServiceAfterFixedInterval(String endpoint, Long interval);

}
