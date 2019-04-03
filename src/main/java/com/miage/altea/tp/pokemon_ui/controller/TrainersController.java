package com.miage.altea.tp.pokemon_ui.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.miage.altea.tp.pokemon_ui.trainers.service.TrainersService;

@Controller
public class TrainersController {

	@Autowired
	private TrainersService service;

	@GetMapping("/trainers/")
	public ModelAndView trainers(Principal principal){
		var modelAndView = new ModelAndView("trainers");
		modelAndView.addObject("pokemonTrainers", service.getAllTrainers(principal.getName()));
		return modelAndView;
	}

	@GetMapping("/profile/")
	public ModelAndView profile(Principal principal){
		var modelAndView = new ModelAndView("profile");
		modelAndView.addObject("profile", service.getTrainer(principal.getName()));
		return modelAndView;
	}
}
