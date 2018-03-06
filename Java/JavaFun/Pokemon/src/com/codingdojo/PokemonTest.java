package com.codingdojo;

public class PokemonTest {
    public static void main(String[] args) {
        Pokemon p1 = new Pokemon("Silvally",10, "bug");
        Pokemon p2 = new Pokemon("Flygon",20, "dragon");
        Pokemon p3 = new Pokemon("Azurill",5, "fairy");

        Pokedex pd = new Pokedex();
        Pokemon p4 = pd.createPokemon("Gengar",30,"ghost");

        System.out.println(pd.pokemonInfo(p4));
        pd.attackPokemon(p3);
        pd.attackPokemon(p2);
        System.out.println(pd.pokemonInfo(p3));
        System.out.println(pd.pokemonInfo(p2));
    }
}
