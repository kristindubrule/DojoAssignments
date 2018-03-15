package com.codingdojo.relationships.services;

import com.codingdojo.relationships.models.Person;
import com.codingdojo.relationships.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void addPerson(Person person) {
        personRepository.save(person);
    }

    public Person findPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public ArrayList<Person> allPersons() {
        return personRepository.findAll();
    }
}
