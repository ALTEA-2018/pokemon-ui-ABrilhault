package com.miage.altea.tp.pokemon_ui.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.miage.altea.tp.pokemon_ui.trainers.bo.Trainer;
import com.miage.altea.tp.pokemon_ui.trainers.service.TrainersService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SecurityConfigTest {

	@Test
	void securityConfig_shouldExtendWebSecurityConfigurerAdapter(){
		assertTrue(WebSecurityConfigurerAdapter.class.isAssignableFrom(SecurityConfig.class));
	}

	@Test
	void passwordEncoder_shouldBeBCryptPasswordEncoder() {
		var securityConfig = new SecurityConfig();
		var passwordEncoder = securityConfig.passwordEncoder();
		assertNotNull(passwordEncoder);
		assertEquals(BCryptPasswordEncoder.class, passwordEncoder.getClass());
	}

	@Test
	void userDetailsService_shouldUseTrainerService() {
		var securityConfig = new SecurityConfig();

		var trainerService = mock(TrainersService.class);
		var trainer = new Trainer();
		trainer.setName("Garry");
		trainer.setPassword("secret");
		when(trainerService.getTrainer("Garry")).thenReturn(trainer);

		securityConfig.setTrainerService(trainerService);

		var userDetailsService = securityConfig.userDetailsService();

		var garry = userDetailsService.loadUserByUsername("Garry");

		// mock should be called
		verify(trainerService).getTrainer("Garry");

		assertNotNull(garry);
		assertEquals("Garry", garry.getUsername());
		assertEquals("secret", garry.getPassword());
		assertTrue(garry.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
	}

	@Test
	void userDetailsService_shouldThrowABadCredentialsException_whenUserDoesntExists() {
		var securityConfig = new SecurityConfig();

		// the mock returns null
		var trainerService = mock(TrainersService.class);
		securityConfig.setTrainerService(trainerService);

		var userDetailsService = securityConfig.userDetailsService();

		var exception = assertThrows(BadCredentialsException.class, () -> userDetailsService.loadUserByUsername("Garry"));
		assertEquals("No such user", exception.getMessage());

		// mock should be called
		verify(trainerService).getTrainer("Garry");
	}


}
