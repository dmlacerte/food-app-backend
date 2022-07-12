package com.example.food_manager.repository;

import com.example.food_manager.model.GroceryManager;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryManagerRepository extends JpaRepository<GroceryManager, Long> {
    List<GroceryManager> findByNameContaining(String name);
  }
