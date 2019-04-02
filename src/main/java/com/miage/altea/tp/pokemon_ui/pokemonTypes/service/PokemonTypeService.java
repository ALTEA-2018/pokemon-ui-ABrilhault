package com.miage.altea.tp.pokemon_ui.pokemonTypes.service;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.PokemonType;

public interface PokemonTypeService {

	List<PokemonType> listPokemonsTypes();
	void setRestTemplate(RestTemplate restTemplate);
	void setCircuitBreaker(CircuitBreaker circuitBreaker);
	PokemonType getPokemonType(int id);

}
