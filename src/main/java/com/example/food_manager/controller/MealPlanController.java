package com.example.food_manager.controller;

import com.example.food_manager.model.MealPlan;
import com.example.food_manager.repository.MealPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = {"https://food-app-frontend.netlify.app/", "http://localhost:8081"})
@RestController
@RequestMapping("/api")
public class MealPlanController {
    
    @Autowired
	MealPlanRepository mealPlanRepository;

    // @GetMapping("/mymealplan")
    // public ResponseEntity<List<MealPlan>> getAllMealPlanItems() {
    //     try {
    //         List<MealPlan> mealPlanItems = new ArrayList<MealPlan>();

    //         mealPlanRepository.findAll().forEach(mealPlanItems::add);

    //         if (mealPlanItems.isEmpty()) {
    //             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //         } else {
    //             return new ResponseEntity<>(mealPlanItems, HttpStatus.OK);
    //         }
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    @GetMapping("/mymealplan/{day}/{time}")
    public ResponseEntity<MealPlan> getMealPlanItemById(@PathVariable("day") String day, @PathVariable("time") String time) {
        Optional<MealPlan> mealPlanItemData = mealPlanRepository.findByDayAndTime(day, time);

		return mealPlanItemData
				.map(mealPlan -> new ResponseEntity<>(mealPlan, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/mymealplan")
    public ResponseEntity<MealPlan> createMealPlanItem(@RequestBody MealPlan mealPlanItem) {
        try {
            MealPlan _mealPlanItems = mealPlanRepository
                    .save(new MealPlan(mealPlanItem.getDay(), mealPlanItem.getTime(), mealPlanItem.getDescription()));
            return new ResponseEntity<>(_mealPlanItems, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/mymealplan/{id}")
    public ResponseEntity<MealPlan> updateMealPlanItem(@PathVariable("id") long id, @RequestBody MealPlan mealPlanItem) {
        Optional<MealPlan> mealPlanItemData = mealPlanRepository.findById(id);

		if (mealPlanItemData.isPresent()) {
			MealPlan _mealPlanItems = mealPlanItemData.get();
			_mealPlanItems.setDay(mealPlanItem.getDay());
			_mealPlanItems.setTime(mealPlanItem.getTime());
            _mealPlanItems.setDescription(mealPlanItem.getDescription());
			return new ResponseEntity<>(mealPlanRepository.save(_mealPlanItems), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }

    @DeleteMapping("/mymealplan/{id}")
	public ResponseEntity<HttpStatus> deleteMealPlanItem(@PathVariable("id") long id) {
		try {
			mealPlanRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
