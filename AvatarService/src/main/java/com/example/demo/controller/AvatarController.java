package com.example.demo.controller;

import com.example.demo.dto.AvatarDTO;
import com.example.demo.service.AvatarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("avatars")
public class AvatarController {
    private final AvatarService avatarService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AvatarDTO> getAll(){
        return avatarService.findAll();
    }

    @GetMapping("{avatarId}")
    @ResponseStatus(HttpStatus.OK)
    public AvatarDTO getById(@PathVariable("avatarId") String id){
        return avatarService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AvatarDTO save(@RequestBody AvatarDTO dto){
        return avatarService.save(dto);
    }

    @PutMapping("{avatarId}")
    @ResponseStatus(HttpStatus.OK)
    public AvatarDTO update(@RequestBody AvatarDTO dto, @PathVariable("avatarId") String id){
        return avatarService.update(dto, id);
    }

    @DeleteMapping("{avatarId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("avatarId") String id){
         avatarService.delete(id);
    }

    @GetMapping("verifyReference/{avatarId}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean verifyReference(@PathVariable("avatarId") String id){
        return avatarService.verifyReference(id);
    }

    @PutMapping("/addElement/{avatarId}/{elementId}")
    @ResponseStatus(HttpStatus.OK)
    public AvatarDTO update(@PathVariable("avatarId") String avatarId, @PathVariable("elementId") String elementId){
        return avatarService.addElement(avatarId, elementId);
    }

}
