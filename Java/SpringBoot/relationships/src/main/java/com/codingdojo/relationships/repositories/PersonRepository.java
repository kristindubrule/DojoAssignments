package com.codingdojo.relationships.repositories;

import com.codingdojo.relationships.models.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PersonRepository extends CrudRepository<Person,Long> {
    ArrayList<Person> findAll();
}
