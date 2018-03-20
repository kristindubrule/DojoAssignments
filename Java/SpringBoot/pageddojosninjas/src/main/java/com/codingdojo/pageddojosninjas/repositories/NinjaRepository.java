package com.codingdojo.pageddojosninjas.repositories;

import com.codingdojo.pageddojosninjas.models.Ninja;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NinjaRepository extends PagingAndSortingRepository<Ninja,Long> {
}
