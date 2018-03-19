package com.codingdojo.dojooverflow.repositories;

import org.springframework.data.repository.CrudRepository;
import com.codingdojo.dojooverflow.models.Question;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
    ArrayList<Question> findAll();
}
