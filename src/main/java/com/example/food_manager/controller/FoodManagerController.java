package com.example.food_manager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.food_manager.model.FoodManager;
import com.example.food_manager.repository.FoodManagerRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class FoodManagerController {
    
    @Autowired
	FoodManagerRepository foodManagerRepository;

	@GetMapping("/myfood")
	public ResponseEntity<List<FoodManager>> getAllFoodItems(@RequestParam(required = false) String name) {
		try {
			List<FoodManager> foodItems = new ArrayList<FoodManager>();

			if (name == null)
				foodManagerRepository.findAll().forEach(foodItems::add);
			else
				foodManagerRepository.findByNameContaining(name).forEach(foodItems::add);

			if (foodItems.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(foodItems, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/myfood/{id}")
	public ResponseEntity<FoodManager> getFoodItemById(@PathVariable("id") long id) {
		Optional<FoodManager> foodItemData = foodManagerRepository.findById(id);

		if (foodItemData.isPresent()) {
			return new ResponseEntity<>(foodItemData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/myfood")
	public ResponseEntity<FoodManager> createFoodItem(@RequestBody FoodManager foodItem) {
		try {
			FoodManager _foodItems = foodManagerRepository
					.save(new FoodManager(foodItem.getName(), foodItem.getType(), foodItem.getDaysToExp()));
			return new ResponseEntity<>(_foodItems, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/myfood/{id}")
	public ResponseEntity<FoodManager> updateFoodItem(@PathVariable("id") long id, @RequestBody FoodManager tutorial) {
		Optional<FoodManager> tutorialData = foodManagerRepository.findById(id);

		if (tutorialData.isPresent()) {
			FoodManager _foodItems = tutorialData.get();
			_foodItems.setName(tutorial.getName());
			_foodItems.setType(tutorial.getType());
			_foodItems.setDaysToExp(tutorial.getDaysToExp());
			return new ResponseEntity<>(foodManagerRepository.save(_foodItems), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/myfood/{id}")
	public ResponseEntity<HttpStatus> deleteFoodItem(@PathVariable("id") long id) {
		try {
			foodManagerRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/myfood")
	public ResponseEntity<HttpStatus> deleteAllFoodItems() {
		try {
			foodManagerRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
