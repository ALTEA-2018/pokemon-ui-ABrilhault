package com.miage.altea.tp.pokemon_ui.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

	@Value("${trainers.service.username}")
	private String user;

	@Value("${trainers.service.password}")
	private String password;

	@Bean
	RestTemplate trainerApiRestTemplate(){
		var rt = restTemplate();
		rt.getInterceptors().add(
				new BasicAuthenticationInterceptor(user, password));
		return rt;
	}

	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
