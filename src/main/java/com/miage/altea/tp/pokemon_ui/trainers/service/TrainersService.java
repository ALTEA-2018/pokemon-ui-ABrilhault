package com.miage.altea.tp.pokemon_ui.trainers.service;

import java.util.List;

import com.miage.altea.tp.pokemon_ui.trainers.bo.Trainer;

public interface TrainersService {

	List<Trainer> listTrainers();

	List<Trainer> getAllTrainers(String name);

	Trainer getTrainer(String name);

}
