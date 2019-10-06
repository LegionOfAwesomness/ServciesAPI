package com.project.shoponline.dao.CommisonAndReward;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.shoponline.model.module1.Address;
import com.project.shoponline.model.module2.Reward;

@Repository
public interface RewardRepository extends CrudRepository<Reward, Long> {
}
