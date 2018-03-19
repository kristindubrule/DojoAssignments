package com.codingdojo.world.repositories;

import com.codingdojo.world.models.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.codingdojo.world.models.Language;

@Repository
public interface LanguageRepository extends CrudRepository<Language,Long> {
    List<Language> findAll();

    @Query("select l from Language l where percentage > 89 order by l.percentage DESC")
    List<Language> langPercentage();


}
