package com.codingdojo.world.controllers;

import com.codingdojo.world.models.City;
import com.codingdojo.world.models.Country;
import com.codingdojo.world.models.Language;
import com.codingdojo.world.services.ApiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class WorldController {
    private final ApiService apiService;

    public WorldController(ApiService apiService) {
        this.apiService = apiService;
    }

    @RequestMapping("slovene")
    public String speakSlovene() {
        List<Object[]> languages = apiService.speakSlovene();
        for (Object[] object : languages) {
            System.out.println(object[0] + " " + object[1] + " " + object[2]);
        }
        return "index";
    }

    @RequestMapping("bycitycount")
    public String cityCount() {
        List<Object[]> countries = apiService.cityCount();
        for (Object[] object : countries) {
            System.out.println(object[0] + " " + object[1]);
        }
        return "index";
    }

    @RequestMapping("populationOver500k")
    public String populationOver500k () {
        List<City> cities = apiService.populationOver500k();
        for (City city : cities) {
            System.out.println(city.getName() + " " + city.getPopulation());
        }
        return "index";
    }

    @RequestMapping("langPercentage")
    public String langPercentage() {
        List<Language> languages = apiService.langPercentage();
        for (Language lang : languages) {
            System.out.println(lang.getCountry().getName() + " " + lang.getLanguage() + " " + lang.getPercentage());
        }
        return "index";
    }


    @RequestMapping("dense")
    public String dense() {
        List<Country> countries = apiService.bySurfaceAreaLTPopulationGT();
        for (Country country : countries) {
            System.out.println(country.getName() + " " + country.getPopulation() + " " + country.getSurface_area());
        }
        return "index";
    }

    @RequestMapping("cm")
    public String cmSA200LE75() {
        List<Country> countries = apiService.cmSA200LE75();
        for (Country country : countries) {
            System.out.println(country.getName() + " " + country.getPopulation() + " " + country.getSurface_area());
        }
        return "index";
    }

    @RequestMapping("buenosaires")
    public String buenosaires() {
        List<Object[]> cities = apiService.BuenosAiresCities();
        for (Object[] object : cities) {
            System.out.println(object[0] + " " + ((Country)object[1]).getName() + " " + object[2] + " " + object[3]);
        }
        return "index";
    }

    @RequestMapping("region")
    public String region() {
        List<Object[]> countries = apiService.countriesByRegion();
        for (Object[] object : countries) {
            System.out.println(object[0] + " " + object[1]);
        }
        return "index";
    }
}
