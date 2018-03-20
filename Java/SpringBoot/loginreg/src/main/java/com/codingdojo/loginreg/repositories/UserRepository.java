package com.codingdojo.loginreg.repositories;

import org.springframework.data.repository.CrudRepository;
import com.codingdojo.loginreg.models.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
