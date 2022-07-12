package com.example.food_manager.model;

import javax.persistence.*;
@Entity
@Table(name = "meal_plan_items")
public class MealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "day")
    private String day;
    @Column(name = "time")
    private String time;
    @Column(name = "description")
    private String description;

    public MealPlan() {

    }

    public MealPlan(String day, String time, String description) {
        this.day = day;
        this.time = time;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
	public String toString() {
		return "Meal Plan Item [day=" + day + ", time=" + time + ", description=" + description + "]";
	}
}
