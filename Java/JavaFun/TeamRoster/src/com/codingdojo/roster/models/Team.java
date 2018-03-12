package com.codingdojo.roster.models;
import java.util.ArrayList;
import java.util.ListIterator;

public class Team {
    private static Integer teamSequence = 0;
    private String name;
    private Integer ID;
    private ArrayList<Player> players;

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Team(String name) {
        this.name = name;

        this.ID = teamSequence;
        teamSequence++;
        this.players = new ArrayList<>();
    }

    public Integer getID() {
        System.out.println("Returning " + ID);
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer playerCount() {
        return players.size();
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public void addPlayer(String firstName, String lastName, Integer age) {
        players.add(new Player(firstName, lastName, age));
    }

    public void removePlayer(Integer playerID) {
        ListIterator li = players.listIterator();
        Player p;
        while (li.hasNext()) {
            p = (Player)li.next();
            if (p.getID() == playerID) {
                li.remove();
            }
        }
    }
}
