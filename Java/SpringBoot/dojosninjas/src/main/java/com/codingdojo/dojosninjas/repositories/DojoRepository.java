package com.codingdojo.dojosninjas.repositories;

import com.codingdojo.dojosninjas.models.Dojo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface DojoRepository extends CrudRepository<Dojo,Long> {
    ArrayList<Dojo> findAll();
}
