package com.miage.altea.tp.pokemon_ui.pokemonTypes.bo;

import java.io.Serializable;

public class Pokemon implements Serializable {

	private int id;

	private int pokemonType;

	private PokemonType type;

	private int level;

	public Pokemon() {
	}

	public Pokemon(int pokemonType, int level) {
		this.pokemonType = pokemonType;
		this.level = level;
	}

	public Pokemon(int id, int pokemonType, int level) {
		this.id = id;
		this.pokemonType = pokemonType;
		this.level = level;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPokemonType() {
		return pokemonType;
	}

	public void setPokemonType(int pokemonType) {
		this.pokemonType = pokemonType;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public PokemonType getType() {
		return type;
	}

	public void setType(PokemonType type) {
		this.type = type;
	}
}
