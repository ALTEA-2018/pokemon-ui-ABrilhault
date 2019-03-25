package com.miage.altea.tp.pokemon_ui.pokemonTypes.bo;

import java.io.Serializable;
import java.util.List;

public class Trainer implements Serializable {

	private String name;

	private List<Pokemon> team;

	public Trainer() {
	}

	public Trainer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Pokemon> getTeam() {
		return team;
	}

	public void setTeam(List<Pokemon> team) {
		this.team = team;
	}
}
