package com.example.demo.service;

import com.example.demo.model.Element;
import com.example.demo.dto.ElementDTO;
import com.example.demo.dto.ElementType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.example.demo.repository.ElementRepository;

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

    @Override
    public List<ElementDTO> findAll() {
        return elementRepository.findAll().stream()
                .map(element -> modelMapper.map(element, ElementDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ElementDTO getById(String id) {
        var retVal = elementRepository.findById(id).orElseThrow(()-> new RuntimeException("Data not found"));
        return  modelMapper.map(retVal, ElementDTO.class);
    }

    @Override
    public Boolean verifyReference(String id) {
        try {
            elementRepository.findById(id).orElseThrow(()-> new RuntimeException("Data not found"));
            return true;
        }
        catch(Exception ex) {
            return false;
        }
    }
}
