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
import org.springframework.web.bind.annotation.RestController;

import com.example.food_manager.model.GroceryManager;
import com.example.food_manager.repository.GroceryManagerRepository;

@CrossOrigin(origins = {"https://food-app-frontend.netlify.app/", "http://localhost:8081"})
@RestController
@RequestMapping("/api")
public class GroceryManagerController {
    
    @Autowired
	GroceryManagerRepository groceryManagerRepository;

    @GetMapping("/mygrocery")
    public ResponseEntity<List<GroceryManager>> getAllGroceryItems() {
        try {
            List<GroceryManager> groceryItems = new ArrayList<GroceryManager>();

            groceryManagerRepository.findAll().forEach(groceryItems::add);

            if (groceryItems.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(groceryItems, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/mygrocery/{id}")
    public ResponseEntity<GroceryManager> getGroceryItemById(@PathVariable("id") long id) {
        Optional<GroceryManager> groceryItemData = groceryManagerRepository.findById(id);

		if (groceryItemData.isPresent()) {
			return new ResponseEntity<>(groceryItemData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }

    @PostMapping("/mygrocery")
    public ResponseEntity<GroceryManager> createGroceryItem(@RequestBody GroceryManager groceryItem) {
        try {
            GroceryManager _groceryItems = groceryManagerRepository
                    .save(new GroceryManager(groceryItem.getName(), groceryItem.getType()));
            return new ResponseEntity<>(_groceryItems, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/mygrocery/{id}")
    public ResponseEntity<GroceryManager> updateGroceryItem(@PathVariable("id") long id, @RequestBody GroceryManager groceryItem) {
        Optional<GroceryManager> groceryItemData = groceryManagerRepository.findById(id);

		if (groceryItemData.isPresent()) {
			GroceryManager _groceryItems = groceryItemData.get();
			_groceryItems.setName(groceryItem.getName());
			_groceryItems.setType(groceryItem.getType());
			return new ResponseEntity<>(groceryManagerRepository.save(_groceryItems), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }

    @DeleteMapping("/mygrocery/{id}")
	public ResponseEntity<HttpStatus> deleteGroceryItem(@PathVariable("id") long id) {
		try {
			groceryManagerRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
