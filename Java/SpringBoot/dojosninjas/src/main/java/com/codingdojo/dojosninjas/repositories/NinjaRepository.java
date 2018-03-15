package com.codingdojo.dojosninjas.repositories;

import com.codingdojo.dojosninjas.models.Ninja;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NinjaRepository extends CrudRepository<Ninja,Long> {
}
