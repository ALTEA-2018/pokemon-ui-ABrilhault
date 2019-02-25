package com.miage.altea.tp.pokemon_ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.service.PokemonTypeService;

@Controller
public class PokemonTypeController {

	@Autowired
	private PokemonTypeService service;

	@GetMapping("/pokedex")
	public ModelAndView pokedex(){
		var modelAndView = new ModelAndView("pokedex");
		modelAndView.addObject("pokemonTypes", service.listPokemonsTypes());
		return modelAndView;	}

	 public void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
		this.service = pokemonTypeService;
	 }



}
