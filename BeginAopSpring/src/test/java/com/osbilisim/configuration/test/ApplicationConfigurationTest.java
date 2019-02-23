package com.osbilisim.configuration.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.osbilisim.controllers.PaymentController;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.osbilisim.controllers")
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@ImportResource(locations = "classpath:applicationContext.xml")

public class ApplicationConfigurationTest {
	@Bean
	public PaymentController paymentController() {
		return new PaymentController();
	}
	
}
