package com.codingdojo.objectmaster;

public class HumanTest {
    public static void main(String[] args) {
        Human tara = new Human();
        Human pam = new Human();

        tara.attack(pam);
        tara.attack(pam);
        System.out.println(pam.getHealth());

        pam.attack(tara);
        System.out.println(tara.getHealth());

        Wizard hermione = new Wizard();

        Ninja leo = new Ninja();
        Samurai jack = new Samurai();

        hermione.printStats();
        leo.printStats();
        jack.printStats();

        System.out.println("***Heal***");
        pam.printStats();
        hermione.printStats();
        hermione.heal(pam);
        pam.printStats();
        hermione.printStats();

        System.out.println("***Fireball***");
        tara.printStats();
        hermione.printStats();
        hermione.fireball(tara);
        tara.printStats();
        hermione.printStats();

        System.out.println("***Steal***");
        tara.printStats();
        leo.printStats();
        leo.steal(tara);
        tara.printStats();
        leo.printStats();

        System.out.println("***runAway***");
        leo.printStats();
        leo.runAway();
        leo.printStats();

        System.out.println("***DeathBlow***");
        tara.printStats();
        jack.printStats();
        jack.deathBlow(tara);
        tara.printStats();
        jack.printStats();

        System.out.println("***Meditate***");
        jack.printStats();
        jack.meditate();
        jack.printStats();

        Samurai sam = new Samurai();
        Samurai bob = new Samurai();
        System.out.println(Samurai.howMany());

    }
}
