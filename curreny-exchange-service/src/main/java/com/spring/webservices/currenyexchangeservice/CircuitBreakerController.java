package com.spring.webservices.currenyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

	@GetMapping("/sample-api")
//	@Retry(name = "sample-api", fallbackMethod = "hardCoddedResponse")
	//@CircuitBreaker(name = "default", fallbackMethod = "hardCoddedResponse")
	//@RateLimiter(name="default")
	@Bulkhead(name="sample-api")
	public String sampleApi() {
		logger.info(" sample api call");
		ResponseEntity<String> forEntity = new  RestTemplate().getForEntity("http://localhost:8888", String.class);
		return forEntity.getBody();
	}

	public String hardCoddedResponse(Exception ex) {

		logger.info("Fall BAck Response");
		return "fall back Respomnse";
	}
}
