package com.codingdojo.web.models;

public class Dog extends Animal implements Pet {
    public String showAffection() {
        if (this.weight >= 50) {
            return this.name + " hopped into your lap and cuddled you! Ooft.";
        } else {
            return this.name + " wants to be picked up and cuddled. Awww.";
        }
    }

    public Dog(String name, String breed, double weight) {
        super(name, breed, weight);
    }
}
