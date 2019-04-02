package com.miage.altea.tp.pokemon_ui.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerOpenException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.web.client.RestTemplate;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.PokemonType;
import com.miage.altea.tp.pokemon_ui.pokemonTypes.service.PokemonTypeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PokemonTypeServiceImplTest {

	@Autowired
	PokemonTypeService pokemonTypeService;

	@Mock
	RestTemplate restTemplate;

	@Value("${pokemonType.service.url}/pokemon-types/{id}")
	String expectedUrl;

	@Autowired
	CacheManager cacheManager;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		pokemonTypeService.setRestTemplate(restTemplate);

		var pikachu = new PokemonType();
		pikachu.setId(25);
		pikachu.setName("Pikachu");
		when(restTemplate.getForObject(expectedUrl, PokemonType.class, 25)).thenReturn(pikachu);
	}

	@Test
	void getPokemonType_shouldUseCache() {
		pokemonTypeService.getPokemonType(25);

		// rest template should have been called once
		verify(restTemplate).getForObject(expectedUrl, PokemonType.class, 25);

		pokemonTypeService.getPokemonType(25);

		// rest template should not be called anymore because result is in cache !
		verifyNoMoreInteractions(restTemplate);

		// one result should be in cache !
		var cachedValue = cacheManager.getCache("pokemons").get(25).get();
		assertNotNull(cachedValue);
		assertEquals(PokemonType.class, cachedValue.getClass());
		assertEquals("Pikachu", ((PokemonType)cachedValue).getName());
	}

	@Test
	void getPokemonType_shouldCircuitBreak(){
		var circuitBreaker = CircuitBreaker.of("pokemon-types", CircuitBreakerConfig.custom().ringBufferSizeInClosedState(2).build());

		pokemonTypeService.setCircuitBreaker(circuitBreaker);
		// the circuit should first be closed
		assertEquals(CircuitBreaker.State.CLOSED, circuitBreaker.getState());
		// registering 2 failures for the circuit to open
		for (int i = 0; i < 2; i++) {
			circuitBreaker.onError(0, new Exception());
		}

		assertEquals(CircuitBreaker.State.OPEN, circuitBreaker.getState());
		// the call should not happen
		assertThrows(CircuitBreakerOpenException.class, () -> pokemonTypeService.getPokemonType(25));
		// restTemplate should not have been called
		verifyZeroInteractions(restTemplate);
	}
}
