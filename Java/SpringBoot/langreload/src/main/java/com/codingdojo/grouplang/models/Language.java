package com.codingdojo.grouplang.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="languages")
public class Language {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Size(min = 2, max = 20)
    private String name;

    @Column
    @Size(min = 2, max = 20)
    private String creator;

    @Column
    @Size(min = 1)
    private String currentVersion;

    @Column(updatable = false)
    @DateTimeFormat(pattern="MM/dd/yyyy HH:mm:ss")
    private Date createdAt;

    @Column
    @DateTimeFormat(pattern="MM/dd/yyyy HH:mm:ss")
    private Date updatedAt;

    public Language() {
    }

    public Language(@Size(min = 2, max = 20) String name, @Size(min = 2, max = 20) String creator, @NotNull String currentVersion) {
        this.name = name;
        this.creator = creator;
        this.currentVersion = currentVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
