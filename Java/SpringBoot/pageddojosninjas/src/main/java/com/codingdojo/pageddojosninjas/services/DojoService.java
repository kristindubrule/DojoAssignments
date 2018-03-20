package com.codingdojo.pageddojosninjas.services;

import com.codingdojo.pageddojosninjas.models.Dojo;
import com.codingdojo.pageddojosninjas.repositories.DojoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DojoService {
    private final DojoRepository dojoRepository;

    public DojoService(DojoRepository dojoRepository) {
        this.dojoRepository = dojoRepository;
    }

    public ArrayList<Dojo> allDojos() {
        return dojoRepository.findAll();
    }

    public void addDojo(Dojo dojo) {
        dojoRepository.save(dojo);
    }

    public Dojo findById(Long id) {
        return dojoRepository.findById(id).orElse(null);
    }
}
