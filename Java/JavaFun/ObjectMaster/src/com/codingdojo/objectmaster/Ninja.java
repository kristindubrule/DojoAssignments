package com.codingdojo.objectmaster;

public class Ninja extends Human {
    public Ninja () {
        this.setStealth(10);
    }

    public void steal(Human human) {
        human.health -= this.stealth;
        this.health += this.stealth;
    }

    public void runAway() {
        this.health -= 10;
    }
}
