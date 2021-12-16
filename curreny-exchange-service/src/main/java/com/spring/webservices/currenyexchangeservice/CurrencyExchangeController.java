package com.spring.webservices.currenyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	@Autowired
	CurrencyExchangeRepository repository;
	
	@Autowired
	private Environment env;
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {

		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
		if(currencyExchange==null)
			throw new RuntimeException(" unable to find " + from +" to "+to);
		String port = env.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		
		return currencyExchange;
	}

}
