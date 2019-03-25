package com.miage.altea.tp.pokemon_ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.service.TrainersService;

@Controller
public class TrainersController {

	@Autowired
	private TrainersService service;

	@GetMapping("/trainers/")
	public ModelAndView trainers(){
		var modelAndView = new ModelAndView("trainers");
		modelAndView.addObject("pokemonTrainers", service.listTrainers());
		return modelAndView;	}
}
