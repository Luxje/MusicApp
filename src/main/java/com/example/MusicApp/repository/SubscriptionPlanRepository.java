package com.example.MusicApp.repository;

import com.example.MusicApp.entity.SubscriptionPlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface SubscriptionPlanRepository extends CrudRepository<SubscriptionPlan, Integer> {
    public SubscriptionPlan findBySubscriptionPlanID(Integer id);
}
