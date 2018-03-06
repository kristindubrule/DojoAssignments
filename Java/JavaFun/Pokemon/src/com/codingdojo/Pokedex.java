package com.codingdojo;

public class Pokedex extends AbstractPokemon {
    public String pokemonInfo(Pokemon pokemon) {
        return "Name: " + pokemon.name + " Health: " + pokemon.health + " Type: " + pokemon.type;
    }
}
