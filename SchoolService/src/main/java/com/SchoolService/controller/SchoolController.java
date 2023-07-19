package com.SchoolService.controller;

import com.SchoolService.domain.School;
import com.SchoolService.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @GetMapping("/get/{id}")
    public School getSchoolById(@PathVariable Long id) {
        return schoolService.view(id);
    }

    @PostMapping("/add")
    public void addSchool(@RequestBody School school) {
        schoolService.add(school);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        schoolService.remove(id);
    }

    @PutMapping("/update")
    public School updateSchool(@RequestBody School school) {
       return schoolService.update(school);
    }

}
