package com.example.MusicApp.repository;

import com.example.MusicApp.model.SubscriptionPlan;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionPlanRepository extends CrudRepository<SubscriptionPlan, Integer> {
    public SubscriptionPlan findBySubscriptionPlanID(Integer id);
}
