package com.codingdojo.zookeeper;

import com.codingdojo.zookeeper.Mammal;

public class Gorilla extends Mammal{
    private int satisfaction;

    public Gorilla(int satisfaction) {
        super();
        this.satisfaction = satisfaction;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }

    public Gorilla() {
        this(50);
    }

    public void throwSomething() {
        System.out.println("The gorilla threw something!");
        this.energyLevel -= 5;
    }

    public void eatBananas() {
        System.out.println("This gorilla likes bananas.");
        this.energyLevel += 10;
    }

    public void climb() {
        System.out.println("The gorilla is climbing!");
        this.energyLevel -= 10;
    }
}
