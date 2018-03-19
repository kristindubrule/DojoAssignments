package com.codingdojo.dojooverflow.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.codingdojo.dojooverflow.models.*;

import java.util.ArrayList;

@Repository
public interface TagRepository extends CrudRepository<Tag,Long> {
    ArrayList<Tag> findAll();
    ArrayList<Tag> findByQuestions(Question question);
    Tag findTagBySubject(String subject);
    Boolean existsTagBySubject(String subject);
}
