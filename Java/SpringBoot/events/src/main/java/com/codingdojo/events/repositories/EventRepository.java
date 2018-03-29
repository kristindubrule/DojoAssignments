package com.codingdojo.events.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.codingdojo.events.models.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event,Long> {
    List<Event> findAll();
    List<Event> findByLocationState(String state);

    @Query("select e from Event e where e.locationState <> ?1")
    List<Event> findByLocationStateNot(String state);

    @Query("select e from Event e where ?1 MEMBER OF e.attendees")
    List<Event> findByUser(User user);
}
