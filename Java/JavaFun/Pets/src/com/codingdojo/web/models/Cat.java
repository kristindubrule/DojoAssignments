package com.codingdojo.web.models;

public class Cat extends Animal implements Pet {
    public String showAffection() {
        return "Your " + this.breed + " cat, " + this.name + ", looked at you with some affection. You think.";
    }

    public Cat(String name, String breed, double weight) {
        super(name, breed, weight);
    }
}
