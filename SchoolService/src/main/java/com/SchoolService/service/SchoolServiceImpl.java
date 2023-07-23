package com.SchoolService.service;

import com.SchoolService.domain.Contact;
import com.SchoolService.domain.School;
import com.SchoolService.dto.SchoolDTO;
import com.SchoolService.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements ISchoolService{
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final SchoolRepository schoolRepository;

    @Override
    public SchoolDTO save(SchoolDTO school) {
        var schoolModel = modelMapper.map(school, School.class);
        schoolRepository.save(schoolModel);
        return modelMapper.map(schoolModel, SchoolDTO.class);
    }

    @Override
    public String delete(String id) {
        schoolRepository.deleteById(id);
        return id;
    }

    @Override
    public SchoolDTO update(SchoolDTO school, String id) {
        schoolRepository.findById(id).orElseThrow(()-> new RuntimeException("Data not found"));
        var schoolModel = modelMapper.map(school, School.class);
        schoolModel.setSchoolId(id);
        Contact contact = new Contact(school.getContact().getEmail(),school.getContact().getPhoneNumber());
        schoolModel.setContact(contact);
        schoolRepository.save(schoolModel);
        return modelMapper.map(schoolModel, SchoolDTO.class);
    }

    @Override
    public List<SchoolDTO> findAll() {
        return schoolRepository.findAll().stream()
                .map(school -> modelMapper.map(school, SchoolDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SchoolDTO getById(String id) {
        var retVal = schoolRepository.findById(id).orElseThrow(()-> new RuntimeException("Data not found"));
        return  modelMapper.map(retVal, SchoolDTO.class);
    }

    @Override
    public Boolean verifyReference(String id) {
        try {
            schoolRepository.findById(id).orElseThrow(()-> new RuntimeException("Data not found"));
            return true;
        }
        catch(Exception ex) {
            return false;
        }
    }
}
