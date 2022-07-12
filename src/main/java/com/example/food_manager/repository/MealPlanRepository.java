package com.example.food_manager.repository;

import com.example.food_manager.model.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {
    Optional<MealPlan> findByDayAndTime(String day, String time);
  }
