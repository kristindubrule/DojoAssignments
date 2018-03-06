package com.codingdojo.zookeeper;

public class MammalTest {
    public static void main(String[] args) {
        Gorilla jane = new Gorilla();

        jane.displayEnergy();
        jane.throwSomething();
        jane.throwSomething();
        jane.throwSomething();
        jane.displayEnergy();
        jane.eatBananas();
        jane.eatBananas();
        jane.displayEnergy();
        jane.climb();
        jane.displayEnergy();


        Dragon barb = new Dragon();

        barb.displayEnergy();
        barb.attackTown();
        barb.attackTown();
        barb.attackTown();
        barb.displayEnergy();
        barb.eatHumans();
        barb.eatHumans();
        barb.displayEnergy();
        barb.fly();
        barb.fly();
        barb.displayEnergy();
    }
}
