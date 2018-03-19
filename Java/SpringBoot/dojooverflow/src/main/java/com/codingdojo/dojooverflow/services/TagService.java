package com.codingdojo.dojooverflow.services;

import com.codingdojo.dojooverflow.repositories.TagRepository;
import org.springframework.stereotype.Service;

import com.codingdojo.dojooverflow.models.*;

import java.util.ArrayList;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public ArrayList<Tag> allTags() {
        return tagRepository.findAll();
    }

    public void addTag(Tag tag) {
        if (!tagRepository.existsTagBySubject(tag.getSubject())) {
            tagRepository.save(tag);
        }
    }

    public ArrayList<Tag> findTagsForQuestion(Question question) {
        return tagRepository.findByQuestions(question);
    }
}
