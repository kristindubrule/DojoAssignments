package com.codingdojo.events.repositories;

import org.springframework.data.repository.CrudRepository;
import com.codingdojo.events.models.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
    List<Role> findAll();
    List<Role> findByName(String name);
    Role findFirstByName(String name);
}
