package com.RewardService.service;

import com.RewardService.dto.RewardDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRewardService {

    RewardDTO save(RewardDTO school);
    Long delete(Long id);
    RewardDTO update(RewardDTO school, Long id);
    List<RewardDTO> findAll(Boolean availability);
    RewardDTO getById(Long id);
    Boolean verifyReference(Long id);

    RewardDTO redeemReward(Long id);

    Long getTypeId(Long rewardId);
}
