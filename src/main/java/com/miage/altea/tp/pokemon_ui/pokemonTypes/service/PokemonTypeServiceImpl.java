package com.miage.altea.tp.pokemon_ui.pokemonTypes.service;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;

import java.util.List;
import java.util.Locale;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.PokemonType;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

  private RestTemplate restTemplate;
  private CircuitBreaker circuitBreaker;
	private String pokemonTypeServiceUrl;

	@Cacheable({"pokemons"})
	public List<PokemonType> listPokemonsTypes() {

		Locale locale = LocaleContextHolder.getLocale();
		HttpHeaders headers = new HttpHeaders();
		headers.setAcceptLanguage(Locale.LanguageRange.parse(locale.toLanguageTag()));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		var arrayOfPokemons =  restTemplate.getForObject(pokemonTypeServiceUrl + "/pokemon-types/", PokemonType[].class, entity);
		return Lists.newArrayList(arrayOfPokemons);
	}

	@Cacheable({"pokemons"})
	public PokemonType getPokemonType(int id){
			return this.circuitBreaker
					.executeSupplier(() -> restTemplate.getForObject(pokemonTypeServiceUrl + "/pokemon-types/{id}", PokemonType.class, id));

//		return restTemplate.getForObject(pokemonTypeServiceUrl + "/pokemon-types/{id}", PokemonType.class, id);
	}

	@Autowired
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Autowired
	public void setCircuitBreaker(CircuitBreaker circuitBreaker) {
		this.circuitBreaker = circuitBreaker;
	}

	@Value("${pokemonType.service.url}")
	void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
		this.pokemonTypeServiceUrl = pokemonServiceUrl;
	}

}
