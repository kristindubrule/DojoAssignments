package com.codingdojo.loginreg.repositories;

import org.springframework.data.repository.CrudRepository;
import com.codingdojo.loginreg.models.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
    List<Role> findAll();
    List<Role> findByName(String name);
    Role findFirstByName(String name);
}
