package com.codingdojo.world.services;

import com.codingdojo.world.models.City;
import com.codingdojo.world.models.Country;
import com.codingdojo.world.models.Language;
import com.codingdojo.world.repositories.CityRepository;
import com.codingdojo.world.repositories.CountryRepository;
import com.codingdojo.world.repositories.LanguageRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApiService {
    private final CityRepository cityRepository;
    private final LanguageRepository languageRepository;
    private final CountryRepository countryRepository;

    public ApiService(CityRepository cityRepository, LanguageRepository languageRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.languageRepository = languageRepository;
        this.countryRepository = countryRepository;
    }

    public List<Object[]> speakSlovene() {
        return countryRepository.speakSlovene();
    }

    public List<Object[]> cityCount() {
        return countryRepository.cityCount();
    }

    public List<City> populationOver500k() {
        return cityRepository.populationOver500k();
    }

    public List<Language> langPercentage() {
        return languageRepository.langPercentage();
    }

    public List<Country> bySurfaceAreaLTPopulationGT() {
        return countryRepository.findCountriesBySurface_areaIsLessThanAndPopulationIsGreaterThan(501.00,100000);
    }

    public List<Country> cmSA200LE75() {
        return countryRepository.cmSA200LE75();
    }

    public List<Object[]> BuenosAiresCities() {
        return cityRepository.BuenosAiresCities();
    }

    public List<Object[]> countriesByRegion() {
        return countryRepository.countriesByRegion();
    }
}
