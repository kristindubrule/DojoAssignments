package com.codingdojo.dojooverflow.models;

import com.codingdojo.dojooverflow.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.convert.converter.Converter;
import com.codingdojo.dojooverflow.models.Tag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public final class TagListConverter implements Converter<String,List<Tag>> {
    private final TagRepository tagRepository;

    public TagListConverter(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> convert(String string) {
        if (string == null || string.equals("")) {
            return null;
        } else {
            List<Tag> tagList = new ArrayList<>();
            String[] tagStrings = string.split("\\s*,\\s*");
            for (String ttext : tagStrings) {
                if (tagRepository.existsTagBySubject(ttext)) {
                    Tag foundTag = tagRepository.findTagBySubject(ttext);
                    System.out.println(foundTag);
                    tagList.add(foundTag);
                } else {
                    tagList.add(new Tag(ttext));
                }
            }
            return tagList;
        }
    }
}
