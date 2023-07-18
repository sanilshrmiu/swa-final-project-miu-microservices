package com.SchoolService.service;

import com.SchoolService.dto.SchoolDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISchoolService {
    SchoolDTO save(SchoolDTO school);
    Long delete(Long id);
    SchoolDTO update(SchoolDTO school, Long id);
    List<SchoolDTO> findAll();
    SchoolDTO getById(Long id);
    Boolean verifyReference(Long id);
}
