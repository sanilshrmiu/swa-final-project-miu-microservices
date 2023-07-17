package com.example.demo.service;

import com.example.demo.dto.AvatarDTO;

import java.util.List;

public interface AvatarService {
    AvatarDTO save(AvatarDTO avatar);
    String delete(String id);
    AvatarDTO update(AvatarDTO avatar, String id);

    List<AvatarDTO> findAll();
    AvatarDTO getById(String id);
}
