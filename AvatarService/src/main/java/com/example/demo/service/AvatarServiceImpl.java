package com.example.demo.service;

import com.example.demo.dto.AvatarDTO;
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
}
