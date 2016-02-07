package com.india.healthcare.analysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.india.healthcare.analysis.service.TimerService;
import com.india.healthcare.analysis.service.TimerServiceImpl;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application extends SpringBootServletInitializer {

	private static final String STATEWISE_ENDPOINT = "/statewise";
	private static final Long FIXED_INTERVAL = 2700000L;
	private static String applicationUrl = "http://infinite-falls-8790.herokuapp.com";

	public static void main(final String[] args) {

		SpringApplication.run(Application.class, args);
		TimerService timerService = new TimerServiceImpl();
	/*	timerService.callServiceAfterFixedInterval(
				applicationUrl.concat(STATEWISE_ENDPOINT), FIXED_INTERVAL);*/

	}

	@Override
	protected final SpringApplicationBuilder configure(
			final SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public String getApplicationUrl() {
		return applicationUrl;
	}

}
