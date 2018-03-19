package com.codingdojo.dojooverflow.models;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class WebMvcContext extends WebMvcConfigurerAdapter {
    private final TagListConverter tagListConverter;

    public WebMvcContext(TagListConverter tagListConverter) {
        this.tagListConverter = tagListConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(tagListConverter);
    }
}
