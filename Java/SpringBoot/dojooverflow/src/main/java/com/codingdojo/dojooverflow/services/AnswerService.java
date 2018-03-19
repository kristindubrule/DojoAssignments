package com.codingdojo.dojooverflow.services;

import com.codingdojo.dojooverflow.models.*;
import com.codingdojo.dojooverflow.repositories.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public ArrayList<Answer> allAnswers() {
        return answerRepository.findAll();
    }

    public void addAnswer(Answer answer) {
        answerRepository.save(answer);
    }
}
