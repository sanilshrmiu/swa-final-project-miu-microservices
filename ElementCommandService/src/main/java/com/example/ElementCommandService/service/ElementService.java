package com.example.ElementCommandService.service;

import com.example.ElementCommandService.dto.ElementDTO;

import java.util.List;

public interface ElementService {
    ElementDTO save(ElementDTO element);
    String delete(String id);
    ElementDTO update(ElementDTO element, String id);
}
