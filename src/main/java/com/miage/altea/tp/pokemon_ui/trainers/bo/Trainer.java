package com.miage.altea.tp.pokemon_ui.trainers.bo;

import java.io.Serializable;
import java.util.List;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.Pokemon;

public class Trainer implements Serializable {

	private String name;

	private List<Pokemon> team;

	private String password;

	private String username;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
