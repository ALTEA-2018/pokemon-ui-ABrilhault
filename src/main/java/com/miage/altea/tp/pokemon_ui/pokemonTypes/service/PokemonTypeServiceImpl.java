package com.miage.altea.tp.pokemon_ui.pokemonTypes.service;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.PokemonType;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

  private RestTemplate restTemplate;
	private String pokemonTypeServiceUrl;

	public List<PokemonType> listPokemonsTypes() {

		Locale locale = LocaleContextHolder.getLocale();
		HttpHeaders headers = new HttpHeaders();
		headers.setAcceptLanguage(Locale.LanguageRange.parse(locale.toLanguageTag()));

		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		var arrayOfPokemons =  restTemplate.getForObject(pokemonTypeServiceUrl + "/pokemon-types/", PokemonType[].class, entity);

		return Lists.newArrayList(arrayOfPokemons);
	}

	public PokemonType getPokemonType(int id){
		return restTemplate.getForObject(pokemonTypeServiceUrl + "/pokemon-types/" + id, PokemonType.class);
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
