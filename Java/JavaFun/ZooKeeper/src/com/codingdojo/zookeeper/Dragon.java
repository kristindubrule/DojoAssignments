package com.codingdojo.zookeeper;

public class Dragon extends Mammal {
    public Dragon() {
        this.energyLevel = 300;
    }

    public void fly() {
        if (this.checkEnergy(100)) {
            System.out.println("*thwap* *thwap* *thwap*. The dragon has flown away.");
            this.energyLevel -= 50;
        } else {
            System.out.println("This dragon is too tired to fly.");
        }
    }


    public void eatHumans() {
        System.out.println("Lost another one...");
        this.energyLevel += 25;
    }

    public boolean checkEnergy(int cost) {
        return this.energyLevel >= cost;
    }

    @Deprecated
    public void attackTown() {
        if (checkEnergy(100)) {
            System.out.println("Eeeek! The town is on fire!");
            this.energyLevel -= 100;
        } else {
            System.out.println("This dragon is too tired to attack the town.");
        }
    }
}
