package com.codingdojo.world.repositories;

import com.codingdojo.world.models.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country,Long> {
    List<Country> findAll();

    @Query("select c.name, l.language, l.percentage from Country c JOIN c.languages l where l.language='Slovene' order by l.percentage DESC")
    List<Object[]> speakSlovene();

    @Query("select c.name, count(cy.id) from Country c JOIN c.cities cy group by c.name order by count(cy.id) desc")
    List<Object[]> cityCount();

    @Query("select c from Country c where population > ?2 and surface_area < ?1")
    List<Country> findCountriesBySurface_areaIsLessThanAndPopulationIsGreaterThan(Double surface_area, Integer population);

    @Query("select c from Country c where c.government_form='Constitutional Monarchy' and c.surface_area > 200 and c.life_expectancy > 75")
    List<Country> cmSA200LE75();

    @Query("select c.region, count(c.id) from Country c group by c.region order by count(c.id) desc")
    List<Object[]> countriesByRegion();

}
