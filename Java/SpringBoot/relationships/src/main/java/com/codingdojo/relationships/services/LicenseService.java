package com.codingdojo.relationships.services;

import com.codingdojo.relationships.models.License;
import com.codingdojo.relationships.repositories.LicenseRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class LicenseService {
    private final LicenseRepository licenseRepository;

    public LicenseService(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    public void addLicense(License license) {
        license.setNumber(StringUtils.leftPad(Long.toString(license.getPerson().getId()),10,"0"));
        System.out.println(license.getNumber());
        licenseRepository.save(license);
    }

}
