package com.miage.altea.tp.pokemon_ui.pokemonTypes.service;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.PokemonType;

public interface PokemonTypeService {

	List<PokemonType> listPokemonsTypes();
	void setRestTemplate(RestTemplate restTemplate);
	PokemonType getPokemonType(int id);

}
