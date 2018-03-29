package com.codingdojo.events.repositories;

import org.springframework.data.repository.CrudRepository;
import com.codingdojo.events.models.*;

public interface MessageRepository extends CrudRepository<Message,Long> {
}
