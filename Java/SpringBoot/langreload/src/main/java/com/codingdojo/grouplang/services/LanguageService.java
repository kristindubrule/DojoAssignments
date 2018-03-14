package com.codingdojo.grouplang.services;

import com.codingdojo.grouplang.repositories.LanguageRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.codingdojo.grouplang.models.Language;

@Service
public class LanguageService {
    private LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<Language> allLanguages() {
        return languageRepository.findAll();
    }

    public Language findLanguageById(Long id) {
        Optional<Language> language = languageRepository.findById(id);
        if (language.isPresent()) {
            return language.get();
        } else {
            return null;
        }
    }

    public void addLanguage(Language language) {
        languageRepository.save(language);
    }

    public void updateLanguage(Language language) {
        languageRepository.save(language);
    }

    public boolean destroyLanguage(Long id) {
        try {
            languageRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}
