package com.miage.altea.tp.pokemon_ui.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.miage.altea.tp.pokemon_ui.trainers.bo.Trainer;
import com.miage.altea.tp.pokemon_ui.trainers.service.TrainersService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	TrainersService trainerService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		http.csrf().disable();
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return t -> {
			Trainer trainer = trainerService.getTrainer(t);
			if (trainer == null) throw new BadCredentialsException("No such user");
			return User
					.withUsername(trainer.getName())
					.password(trainer.getPassword())
					.roles("USER")
					.build();
		};
	}


	public TrainersService getTrainerService() {
		return trainerService;
	}

	public void setTrainerService(TrainersService trainerService) {
		this.trainerService = trainerService;
	}
}
