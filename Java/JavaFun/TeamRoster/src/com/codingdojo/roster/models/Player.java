package com.codingdojo.roster.models;

public class Player {
    private static Integer playerSequence = 0;

    private String firstName;
    private String lastName;
    private Integer age;
    private Integer ID;

    public Player(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.ID = playerSequence;
        playerSequence++;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getID() {
        return ID;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
