package com.codingdojo.objectmaster;

public class Wizard extends Human {

    public Wizard() {
        this.setHealth(50);
        this.setIntelligence(8);
    }

    public void heal(Human human) {
        human.setHealth(human.getHealth()+this.getIntelligence());
        this.setHealth(this.getHealth()-this.getIntelligence());
    }

    public void fireball(Human human) {
        int currentHealth = human.getHealth();
        human.setHealth(currentHealth-(3 * this.getIntelligence()));
    }
}
