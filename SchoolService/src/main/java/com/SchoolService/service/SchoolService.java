package com.SchoolService.service;

import com.SchoolService.domain.Contact;
import com.SchoolService.domain.School;
import com.SchoolService.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SchoolService implements ISchoolService {
    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public void add(School school) {
        School school1 = schoolRepository.save(school);

    }

    @Override
    public void remove(Long id) {
        schoolRepository.deleteById(id);

    }

    @Override
    public School update(School updatedSchool) {
        Optional<School> optionalSchool = schoolRepository.findById(updatedSchool.getId());

        if (optionalSchool.isPresent()) {
            School existingSchool = optionalSchool.get();
            Contact contact = new Contact(updatedSchool.getContact().getEmail(),updatedSchool.getContact().getPhone());
            updatedSchool.setContact(contact);
            return schoolRepository.save(updatedSchool);
        } else {
            // School with the given ID not found, you can throw an exception or handle the case as needed
            return null;
        }
    }

    @Override
    public School view(Long id) {
        Optional<School> optionalSchool = schoolRepository.findById(id);

        // Check if the school with the given ID exists
        if (optionalSchool.isPresent()) {
            return optionalSchool.get();
        } else {
            // School with the given ID not found, you can throw an exception or handle the case as needed
            return null;
        }
    }
}