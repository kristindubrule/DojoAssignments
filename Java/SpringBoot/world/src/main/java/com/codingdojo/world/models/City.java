package com.codingdojo.world.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="cities")
public class City {
    @Id
    @GeneratedValue
    private int id;

    @Size(max=35)
    @Column(columnDefinition="char(35)")
    private String name;

    @Size(max=3)
    @Column(name="country_code",columnDefinition="char(3)")
    private String country_code;

    @Size(max=20)
    @Column(columnDefinition="char(20)")
    private String district;

    private int population;

    @ManyToOne
    @JoinColumn(name="country_id")
    private Country country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
