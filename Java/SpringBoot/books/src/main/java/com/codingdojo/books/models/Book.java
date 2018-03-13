package com.codingdojo.books.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Book {
    @Size(min = 3, max = 20)
    private String title;

    @Size(min = 5, max = 200)
    private String description;

    @Size(min = 3, max = 40)
    private String language;

    @Min(100)
    private int numberOfPages;

    public Book(String title, String description, String language, int numberOfPages) {
        this.title = title;
        this.description = description;
        this.language = language;
        this.numberOfPages = numberOfPages;
    }

    public Book() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguages(String languages) {
        this.language = language;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }


}
