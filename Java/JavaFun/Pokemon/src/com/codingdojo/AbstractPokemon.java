package com.codingdojo;

public abstract class AbstractPokemon implements PokemonInterface {
    public Pokemon createPokemon(String name, int health, String type) {
        return new Pokemon(name, health, type);
    }

    public void attackPokemon(Pokemon pokemon) {
        pokemon.health -= 10;
    }
}
