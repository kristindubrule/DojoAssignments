package com.codingdojo.objectmaster;

public class Samurai extends Human {
    static int samurai_count = 0;

    public Samurai() {
        this.setHealth(200);
        samurai_count++;
    }

    public void deathBlow(Human human) {
        human.setHealth(0);
        this.setHealth(this.getHealth()/2);
    }

    private void resethealth() {
        this.setHealth(200);
    }

    public void meditate() {
        this.resethealth();
    }

    public static int howMany() {
        return samurai_count;
    }
}
