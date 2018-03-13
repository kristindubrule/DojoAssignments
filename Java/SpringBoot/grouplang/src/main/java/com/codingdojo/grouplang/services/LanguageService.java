package com.codingdojo.grouplang.services;

import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.codingdojo.grouplang.models.Language;

@Service
public class LanguageService {
    private List<Language> languages = new ArrayList<Language>(Arrays.asList(
            new Language("Java","James Gosling","1.8"),
            new Language("Python","Guido von Rossum","3.6"),
            new Language("JavaScript","Brendan Eich","1.9.5.23.24.7.2")
    ));

    public List<Language> allLanguages() {
        return languages;
    }

    public Language findLanguageByIndex(int index) {
        if (index < languages.size()) {
            return languages.get(index);
        } else {
            return null;
        }
    }

    public void addLanguage(Language language) {
        languages.add(language);
    }

    public void updateLanguage(int id, Language language) {
        if (id < languages.size()) {
            languages.set(id, language);
        }
    }

    public void destroyLanguage(int id) {
        if (id < languages.size()) {
            languages.remove(id);
        }
    }
}
