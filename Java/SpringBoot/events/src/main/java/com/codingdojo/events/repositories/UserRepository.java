package com.codingdojo.events.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.codingdojo.events.models.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);

    Boolean existsByRolesContains(Role role);

    Boolean existsUserByIdEqualsAndRolesContains(Long id, Role role);

    List<User> findAll();

    // select u.id, r.name from User u JOIN u.roles r where r.name = 'ROLE_ADMIN'
    @Query("select u.id, r.name from User u JOIN u.roles r where r.name = 'ROLE_ADMIN'")
    List<Object[]> admins();
}
