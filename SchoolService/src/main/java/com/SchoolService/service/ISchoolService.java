package com.SchoolService.service;

import com.SchoolService.domain.School;

public interface ISchoolService {
    void add(School school);
    void remove(Long id);
    School update(School school);
    School view(Long id);
}
