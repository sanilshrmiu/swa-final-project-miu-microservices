package com.SchoolService.service;

import com.SchoolService.dto.SchoolDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISchoolService {
    SchoolDTO save(SchoolDTO school);
    String delete(String id);
    SchoolDTO update(SchoolDTO school, String id);
    List<SchoolDTO> findAll();
    SchoolDTO getById(String id);
    Boolean verifyReference(String id);
}
