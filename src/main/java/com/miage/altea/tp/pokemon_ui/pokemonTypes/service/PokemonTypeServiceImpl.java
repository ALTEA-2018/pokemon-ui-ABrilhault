package com.miage.altea.tp.pokemon_ui.pokemonTypes.service;

import java.util.List;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.PokemonType;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

  private RestTemplate restTemplate;
	private String pokemonTypeServiceUrl;

	public List<PokemonType> listPokemonsTypes() {
//		var expectedUrl = "http://localhost:8080/pokemon-types";
		var arrayOfPokemons =  restTemplate.getForObject(pokemonTypeServiceUrl + "/pokemon-types", PokemonType[].class);
		return Lists.newArrayList(arrayOfPokemons);
	}

	@Autowired
	void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Value("${pokemonType.service.url}")
	void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
		this.pokemonTypeServiceUrl = pokemonServiceUrl;
	}

}
