package com.codingdojo.auth.repositories;

import org.springframework.data.repository.CrudRepository;
import com.codingdojo.auth.models.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
    List<Role> findAll();
    List<Role> findByName(String name);
}
