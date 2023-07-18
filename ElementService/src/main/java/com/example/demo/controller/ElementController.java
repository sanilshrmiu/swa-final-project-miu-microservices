package com.example.demo.controller;

import com.example.demo.dto.ElementDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.ElementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("elements")
public class ElementController {

    public final ElementService elementService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ElementDTO> getAll() {
        return elementService.findAll();
    }

    @GetMapping("{elementId}")
    @ResponseStatus(HttpStatus.OK)
    public ElementDTO getById(@PathVariable("elementId") String id){
        return elementService.getById(id);
    }

    @GetMapping("verifyReference/{elementId}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean verifyReference(@PathVariable("elementId") String id){
        return elementService.verifyReference(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ElementDTO save(@RequestBody ElementDTO dto){
        return elementService.save(dto);
    }

    @PutMapping("{elementId}")
    @ResponseStatus(HttpStatus.OK)
    public ElementDTO update(@RequestBody ElementDTO dto, @PathVariable("elementId") String id){
        return elementService.update(dto, id);
    }

    @DeleteMapping("{elementId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("elementId") String id){
        elementService.delete(id);
    }
}
