package com.codingdojo.pageddojosninjas.services;

import com.codingdojo.pageddojosninjas.models.Ninja;
import com.codingdojo.pageddojosninjas.repositories.NinjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class NinjaService {
    @Autowired
    private final NinjaRepository ninjaRepository;

    private static final int PAGE_SIZE = 5;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    public void addNinja(Ninja ninja) {
        ninjaRepository.save(ninja);
    }

    public Page<Ninja> ninjasPerPage(int pageNumber) {
        // get all the ninjas page and sort them in ascending order the last name property
        PageRequest pageRequest = new PageRequest(pageNumber, PAGE_SIZE, Sort.Direction.ASC, "dojo.name");
        Page<Ninja> ninjas = ninjaRepository.findAll(pageRequest);
        return ninjaRepository.findAll(pageRequest);
    }
}
