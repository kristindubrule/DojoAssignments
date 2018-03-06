package com.codingdojo;

public class Pokemon {
    static int numberPokemon = 0;

    protected String name;
    protected int health;
    protected String type;

    public static int getNumberPokemon() {
        return numberPokemon;
    }

    public static void setNumberPokemon(int numberPokemon) {
        Pokemon.numberPokemon = numberPokemon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Pokemon(String name, int health, String type) {
        this.name = name;
        this.health = health;
        this.type = type;
    }
}

