package com.codingdojo.roster.models;

import java.util.ArrayList;

public class TeamList {
    ArrayList<Team> teams;

    public TeamList() {
        teams = new ArrayList<Team>();
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void addTeam(String teamName) {
        Team team = new Team(teamName);
        System.out.println(team);
        teams.add(team);

    }

    public Team findTeam(Integer teamID) {
        for (Team t : teams) {
            if (t.getID() == teamID) {
                return t;
            }
        }
        return null;
    }
}
