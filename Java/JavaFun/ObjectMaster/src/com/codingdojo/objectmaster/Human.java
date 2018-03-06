package com.codingdojo.objectmaster;
import java.lang.Object;

public class Human {
    protected int strength = 3;
    protected int stealth = 3;
    protected int intelligence = 3;
    protected int health = 100;

    public void attack(Human human) {
        human.health -= this.strength;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStealth() {
        return stealth;
    }

    public void setStealth(int stealth) {
        this.stealth = stealth;
    }

    public void printStats() {
        System.out.println(this.getClass().getSimpleName() + ":\n   strength: " + this.strength + "\n   intelligence: " + this.intelligence
            + "\n   stealth: " + this.stealth + "\n   health: " + this.health);
    }
}

