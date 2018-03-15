package com.codingdojo.relationships.repositories;

import com.codingdojo.relationships.models.License;
import org.springframework.data.repository.CrudRepository;

public interface LicenseRepository extends CrudRepository<License, Long> {

}
