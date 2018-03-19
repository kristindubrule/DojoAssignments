package com.codingdojo.world.repositories;

import com.codingdojo.world.models.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CityRepository extends CrudRepository<City,Long> {
    List<City> findAll();

    @Query("select c from City c where c.population > 500000 order by population desc")
    List<City> populationOver500k();

    @Query("select c.name, c.country, c.district, c.population from City c where c.district='Buenos Aires' and c.population > 500000")
    List<Object[]> BuenosAiresCities();
}
