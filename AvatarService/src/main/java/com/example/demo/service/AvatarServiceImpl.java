package com.example.demo.service;

import com.example.demo.dto.AvatarDTO;
import com.example.demo.dto.ElementType;
import com.example.demo.model.Avatar;
import com.example.demo.repository.AvatarRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvatarServiceImpl implements AvatarService {
    private final ModelMapper modelMapper;
    private final AvatarRepository avatarRepository;

    private final ElementFeignClient elementFiegnClient;

    @Override
    public AvatarDTO save(AvatarDTO avatar) {
        var avatarModel = modelMapper.map(avatar, Avatar.class);
        avatarRepository.save(avatarModel);
        return modelMapper.map(avatarModel, AvatarDTO.class);
    }

    @Override
    public String delete(String id) {
        avatarRepository.deleteById(id);
        return id;
    }

    @Override
    public AvatarDTO update(AvatarDTO avatar, String id) {
        avatarRepository.findById(id).orElseThrow(()-> new RuntimeException("Data not found"));
        var avatarModel = modelMapper.map(avatar, Avatar.class);
        avatarModel.setId(id);
        avatarRepository.save(avatarModel);
        return modelMapper.map(avatarModel, AvatarDTO.class);
    }

    @Override
    public List<AvatarDTO> findAll() {
        return avatarRepository.findAll().stream()
                .map(avatar -> modelMapper.map(avatar, AvatarDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AvatarDTO getById(String id) {
        var retVal = avatarRepository.findById(id).orElseThrow(()-> new RuntimeException("Data not found"));
        return  modelMapper.map(retVal, AvatarDTO.class);
    }

    @Override
    public Boolean verifyReference(String id) {
        return avatarRepository.findById(id).isPresent();
    }

    @Override
    public AvatarDTO addElement(String avatarId, String elementId) {
        var avatar = avatarRepository.findById(avatarId).orElseThrow(()-> new RuntimeException("Data not found"));
        var element = elementFiegnClient.getById(elementId);

        switch (ElementType.valueOf(element.getType()))
        {
            case hair:
                avatar.setHair(elementId);
                break;
            case head:
                avatar.setHead(elementId);
                break;
            case eye:
                avatar.setEye(elementId);
                break;
            case nose:
                avatar.setNose(elementId);
                break;
            case mouth:
                avatar.setMouth(elementId);
                break;
            case ears:
                avatar.setEars(elementId);
                break;
            case body:
                avatar.setBody(elementId);
                break;
            case  hat:
                avatar.setHat(elementId);
                break;
            case top:
                avatar.setTop(elementId);
                break;
            case topcolour:
                avatar.setTopColor(elementId);
                break;
            case hatcolour:
                avatar.setHatColor(elementId);
                break;

        }

        avatarRepository.save(avatar);
        return modelMapper.map(avatar, AvatarDTO.class);
    }

    private boolean isValidElementType(String value) {
        try {
            ElementType.valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}


