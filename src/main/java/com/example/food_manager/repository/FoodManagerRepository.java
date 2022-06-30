package com.example.food_manager.repository;

import com.example.food_manager.model.FoodManager;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodManagerRepository extends JpaRepository<FoodManager, Long> {
    List<FoodManager> findByNameContaining(String name);
  }
