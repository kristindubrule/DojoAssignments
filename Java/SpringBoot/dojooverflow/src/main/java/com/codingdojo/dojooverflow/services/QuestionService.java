package com.codingdojo.dojooverflow.services;

import com.codingdojo.dojooverflow.repositories.QuestionRepository;
import com.codingdojo.dojooverflow.repositories.TagRepository;
import org.springframework.stereotype.Service;
import com.codingdojo.dojooverflow.models.*;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final TagRepository tagRepository;

    public QuestionService(QuestionRepository questionRepository, TagRepository tagRepository) {
        this.questionRepository = questionRepository;
        this.tagRepository = tagRepository;
    }

    public ArrayList<Question> allQuestions() {
        return questionRepository.findAll();
    }

    public void addQuestion(Question question) {
        questionRepository.save(question);
    }

    public void updateQuestion(Question question) {
        questionRepository.save(question);
    }

    public Question findQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    public void addTag(Question question, ArrayList<Tag> tags) {
        question.getTags().addAll(tags);
    }
}
