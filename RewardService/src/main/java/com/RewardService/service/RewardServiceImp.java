package com.RewardService.service;

import com.RewardService.domain.Reward;
import com.RewardService.domain.RewardType;
import com.RewardService.dto.RewardDTO;
import com.RewardService.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RewardServiceImp implements IRewardService {
    @Autowired
private ModelMapper modelMapper;
    @Autowired
    private final RewardRepository rewardRepository;
    @Override
    public RewardDTO save(RewardDTO reward) {
        if(reward.getRewardType()!=RewardType.ELEMENT&&reward.getElementId()!=null){
            throw new RuntimeException("Element Id cant be saved if reward type is not element");
        }
        var rewardModel = modelMapper.map(reward, Reward.class);
        rewardRepository.save(rewardModel);
        return modelMapper.map(rewardModel, RewardDTO.class);
    }

    @Override
    public Long delete(Long id) {
        rewardRepository.deleteById(id);
        return id;
    }

    @Override
    public RewardDTO update(RewardDTO reward, Long id) {
        rewardRepository.findById(id).orElseThrow(()->new RuntimeException("Data not found"));
        var rewardModel = modelMapper.map(reward, Reward.class);
        rewardModel.setRewardType(reward.getRewardType());

        return modelMapper.map(rewardModel, RewardDTO.class);
    }

    @Override
    public List<RewardDTO> findAll(Boolean availability) {
        if(availability){
            return rewardRepository.findAll().stream()
                    .map(reward -> modelMapper.map(reward, RewardDTO.class))
                    .filter(reward -> reward.getQuantity() > 0)
                    .collect(Collectors.toList());
        }
        return rewardRepository.findAll().stream()
                .map(reward -> modelMapper.map(reward, RewardDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RewardDTO getById(Long id) {
        var retVal = rewardRepository.findById(id).orElseThrow(()->new RuntimeException("Data not found"));
        return modelMapper.map(retVal, RewardDTO.class);
    }


    @Override
    public Boolean verifyReference(Long id) {
        try{
            rewardRepository.findById(id).orElseThrow(()->new RuntimeException("Data not found"));
            return true;

        }
        catch (Exception ex){
            return false;
        }
    }

    @Override
    public RewardDTO redeemReward(Long id) {
        var reward = rewardRepository.findById(id).orElseThrow(()->new RuntimeException("Data not found"));
        reward.setQuantity(reward.getQuantity()-1);
        rewardRepository.save(reward);
        return modelMapper.map(reward, RewardDTO.class);

    }

    public Long getTypeId(Long rewardId){
        var retVal = rewardRepository.findById(rewardId).orElseThrow(()->new RuntimeException("Data not found"));
        return retVal.getRewardTypeId();

    };



}
