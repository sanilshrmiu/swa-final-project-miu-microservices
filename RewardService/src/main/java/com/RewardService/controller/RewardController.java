package com.RewardService.controller;

import com.RewardService.dto.RewardDTO;
import com.RewardService.service.RewardServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rewards")
public class RewardController {
    @Autowired
    private final RewardServiceImp  rewardServiceImp;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RewardDTO> getAll(@RequestParam(value = "availability",defaultValue = "false") Boolean availability){

        return rewardServiceImp.findAll(!availability.equals("false"));
    }

    @GetMapping("/get/{rewardId}")
    @ResponseStatus(HttpStatus.OK)
    public RewardDTO getById(@PathVariable("rewardId") Long id){
        return rewardServiceImp.getById(id);
    }
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public RewardDTO save(@RequestBody RewardDTO dto){
        return rewardServiceImp.save(dto);
    }
    @PutMapping("/update/{rewardId}")
    @ResponseStatus(HttpStatus.OK)
    public RewardDTO update(@RequestBody RewardDTO dto, @PathVariable("rewardId") Long id){
        return rewardServiceImp.update(dto, id);
    }
    @DeleteMapping("/delete/{rewardId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("rewardId") Long id){
        rewardServiceImp.delete(id);
    }
    @GetMapping("/verifyReference/{rewardId}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean verifyReference(@PathVariable("schoolId") Long id){
        return rewardServiceImp.verifyReference(id);
    }
    @PutMapping("/redeem/{rewardId}")
    @ResponseStatus(HttpStatus.OK)
    public RewardDTO redeemReward(@PathVariable ("rewardId") Long id){
        return rewardServiceImp.redeemReward(id);
    }

    @GetMapping("/getTypeId/{rewardId}")
    @ResponseStatus(HttpStatus.OK)
    public Long getRewardTypeId(@PathVariable("rewardId") Long id){
        return rewardServiceImp.getTypeId(id);
    }

}
