package com.codingdojo.grouplang.repositories;

import org.springframework.data.repository.CrudRepository;
import com.codingdojo.grouplang.models.Language;
import java.util.List;

public interface LanguageRepository extends CrudRepository<Language,Long> {
    List<Language> findAll();
}
