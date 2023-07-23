package com.SchoolService.controller;

import com.SchoolService.dto.SchoolDTO;
import com.SchoolService.service.SchoolServiceImpl;
import com.netflix.discovery.converters.Auto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    private final SchoolServiceImpl schoolService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SchoolDTO> getAll(){
        return schoolService.findAll();
    }

    @GetMapping("/get/{schoolId}")
    @ResponseStatus(HttpStatus.OK)
    public SchoolDTO getById(@PathVariable("schoolId") String id){
        return schoolService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public SchoolDTO save(@RequestBody SchoolDTO dto){
        return schoolService.save(dto);
    }

    @PutMapping("/update/{schoolId}")
    @ResponseStatus(HttpStatus.OK)
    public SchoolDTO update(@RequestBody SchoolDTO dto, @PathVariable("schoolId") String id){
        return schoolService.update(dto, id);
    }

    @DeleteMapping("/delete/{schoolId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("schoolId") String id){
        schoolService.delete(id);
    }

    @GetMapping("/verifyReference/{schoolId}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean verifyReference(@PathVariable("schoolId") String id){
        return schoolService.verifyReference(id);
    }

}
