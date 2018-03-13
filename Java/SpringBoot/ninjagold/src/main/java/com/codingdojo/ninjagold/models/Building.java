package com.codingdojo.ninjagold.models;
import com.sun.org.apache.bcel.internal.generic.SWITCH;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Building {
    protected int maxGold;
    protected int minGold;
    protected Message message;

    public Building(int maxGold, int minGold) {
        this.maxGold = maxGold;
        this.minGold = minGold;
    }

    public int visit() {
        int gold = ThreadLocalRandom.current().nextInt(minGold,maxGold);
        setMessage(gold);
        return(gold);
    }

    public Message getMessage() {
        return message;
    }

    protected void setMessage(int gold) {
        this.message = new Message("Earned " + gold + " golds from the " + this.getClass().getSimpleName() + "!","earn");
    }
}

class Farm extends Building {
    public Farm() {
        super(20,10);
    }
}

class Cave extends Building {
    public Cave() {
        super(10,5);
    }
}

class House extends Building {
    public House() {
        super(5,2);
    }
}

class Casino extends Building {
    public Casino() {
        super(50,0);
    }

    @Override
    public int visit() {
        int gold = ThreadLocalRandom.current().nextInt(maxGold*-1,maxGold);
        setMessage(gold);
        return(gold);
    }

    @Override
    public void setMessage(int gold) {
        if (gold == 0) {
            this.message = new Message("Entered a casino and didn't win or lose. Could be worse.","tie");
        } else if (gold < 0) {
            this.message = new Message("Entered a casino and lost " + gold + " golds... Ouch.","lose");
        } else {
            super.setMessage(gold);
        }
    }
}