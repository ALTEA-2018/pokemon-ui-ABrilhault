package com.miage.altea.tp.pokemon_ui.pokemonTypes.service;

import java.util.List;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.Trainer;

@Service
public class TrainersServiceImpl implements TrainersService {

	private RestTemplate restTemplate;
	private String trainerServiceUrl;

	@Autowired
	private PokemonTypeServiceImpl service;

	public List<Trainer> listTrainers() {
		var arrayOfTrainers =  restTemplate.getForObject(trainerServiceUrl + "/trainers/", Trainer[].class);
		var listOfTrainers =  Lists.newArrayList(arrayOfTrainers);

		listOfTrainers.parallelStream().forEach(
				t -> t.getTeam().forEach(
						p -> p.setType(service.getPokemonType(p.getPokemonType()))));
		return listOfTrainers;
	}

	@Autowired
	void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Value("${trainers.service.url}")
	void setTrainerServiceUrl(String trainersServiceUrl) {
		this.trainerServiceUrl = trainersServiceUrl;
	}

}
