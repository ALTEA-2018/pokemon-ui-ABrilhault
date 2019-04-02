package com.miage.altea.tp.pokemon_ui.trainers.service;

import java.util.List;

import com.miage.altea.tp.pokemon_ui.trainers.bo.Trainer;

public interface TrainersService {

	List<Trainer> listTrainers();

	Trainer getTrainer(String name);

}
