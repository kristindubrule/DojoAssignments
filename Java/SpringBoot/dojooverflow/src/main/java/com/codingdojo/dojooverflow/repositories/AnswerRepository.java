package com.codingdojo.dojooverflow.repositories;

import com.codingdojo.dojooverflow.models.*;
import com.codingdojo.dojooverflow.models.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AnswerRepository extends CrudRepository<Answer,Long> {
    ArrayList<Answer> findAll();
    ArrayList<Answer> findByQuestion(Question question);
}
