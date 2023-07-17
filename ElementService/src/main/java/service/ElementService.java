package service;

import dto.ElementDTO;
import model.Element;

import java.util.List;

public interface ElementService {
    ElementDTO save(ElementDTO element);
    String delete(String id);
    ElementDTO update(ElementDTO element, String id);

    List<ElementDTO> findAll();
    ElementDTO getById(String id);

    Boolean verifyReference(String id);
}
