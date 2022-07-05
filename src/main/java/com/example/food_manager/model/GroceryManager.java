package com.example.food_manager.model;

import javax.persistence.*;
@Entity
@Table(name = "grocery_items")
public class GroceryManager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;

    public GroceryManager() {

    }

    public GroceryManager(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
	public String toString() {
		return "Food Item [id=" + id + ", name=" + name + ", type=" + type + "]";
	}
}
