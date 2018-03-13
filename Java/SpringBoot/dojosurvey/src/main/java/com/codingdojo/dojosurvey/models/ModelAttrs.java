package com.codingdojo.dojosurvey.models;

public class ModelAttrs {
    private String name;
    private String location;
    private String language;
    private String comments;

    public ModelAttrs(String name, String location, String language, String comments) {
        this.name = name;
        this.location = location;
        this.language = language;
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getLanguage() {
        return language;
    }

    public String getComments() {
        return comments;
    }
}
