package com.spring.webservices.currenyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

	
	@Autowired
	 private CurrencyExchangeProxy proxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		HashMap<String, String> uriVariables = new HashMap();
		uriVariables.put("to", to);
		uriVariables.put("from", from);

		ResponseEntity<CurrencyConversion> forEntity = new RestTemplate().getForEntity(
				"http://localhost:8100/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
		CurrencyConversion body = forEntity.getBody();
		return new CurrencyConversion(body.getId(), from, to, quantity, body.getConversionMultiple(),
				quantity.multiply(body.getConversionMultiple()), body.getEnvironment());
	}
	
	@GetMapping("/currency-conversion-fiegn/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFiegn(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		CurrencyConversion body = proxy.retrieveExchangeValue(from, to);
		return new CurrencyConversion(body.getId(), from, to, quantity, body.getConversionMultiple(),
				quantity.multiply(body.getConversionMultiple()), body.getEnvironment());
	}
}
