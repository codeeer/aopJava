package com.osbilisim.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.osbilisim.controllers.PaymentController;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.osbilisim.controllers")

@ImportResource(locations = "classpath:applicationContext.xml")
public class ApplicationConfiguration {
	@Bean
	public PaymentController paymentController() {
		return new PaymentController();
	}
}
