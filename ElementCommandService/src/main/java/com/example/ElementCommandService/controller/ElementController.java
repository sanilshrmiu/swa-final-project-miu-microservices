package com.example.ElementCommandService.controller;

import com.example.ElementCommandService.dto.ElementDTO;
import com.example.ElementCommandService.service.ElementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("elements")
public class ElementController {

    public final ElementService elementService;

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
