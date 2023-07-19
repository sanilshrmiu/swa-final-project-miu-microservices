package com.example.ElementCommandService.service;


import com.example.ElementCommandService.dto.ElementDTO;
import com.example.ElementCommandService.dto.ElementType;
import com.example.ElementCommandService.model.Element;
import com.example.ElementCommandService.repository.ElementRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElementServiceImpl implements ElementService {
    private final ModelMapper modelMapper;
    private final ElementRepository elementRepository;

    private boolean isValidElementType(String value) {
        try {
            ElementType.valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public ElementDTO save(ElementDTO element) {
        if(!isValidElementType(element.getType())){
            throw new RuntimeException("Invalid Element Type");
        }
        var model = modelMapper.map(element, Element.class);
        elementRepository.save(model);
        return modelMapper.map(model, ElementDTO.class);
    }

    @Override
    public String delete(String id) {
        elementRepository.deleteById(id);
        return id;
    }

    @Override
    public ElementDTO update(ElementDTO element, String id) {
        if(!isValidElementType(element.getType())){
            throw new RuntimeException("Invalid Element Type");
        }

        elementRepository.findById(id).orElseThrow(()-> new RuntimeException("Data not found"));
        var model = modelMapper.map(element, Element.class);
        model.setId(id);
        elementRepository.save(model);
        return modelMapper.map(model, ElementDTO.class);
    }

}
