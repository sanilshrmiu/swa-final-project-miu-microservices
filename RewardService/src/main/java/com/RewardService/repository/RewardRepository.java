package com.RewardService.repository;

import com.RewardService.domain.Reward;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RewardRepository extends MongoRepository<Reward,Long> {

}
