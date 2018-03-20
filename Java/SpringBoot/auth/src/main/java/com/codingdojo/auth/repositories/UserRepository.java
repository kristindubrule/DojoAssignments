package com.codingdojo.auth.repositories;

import org.springframework.data.repository.CrudRepository;
import com.codingdojo.auth.models.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
