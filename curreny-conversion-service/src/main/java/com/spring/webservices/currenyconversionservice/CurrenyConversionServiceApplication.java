package com.spring.webservices.currenyconversionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrenyConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrenyConversionServiceApplication.class, args);
	}

}
