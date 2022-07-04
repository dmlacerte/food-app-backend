package com.example.food_manager.model;

import javax.persistence.*;
@Entity
@Table(name = "food_items")
public class FoodManager {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "type")
	private String type;
	@Column(name = "days_to_exp")
	private int daysToExp;
	@Column(name = "use_this_week")
	private boolean useThisWeek;

    public FoodManager() {

	}

	public FoodManager(String name, String type, int daysToExp, boolean useThisWeek) {
		this.name = name;
		this.type = type;
		this.daysToExp = daysToExp;
		this.useThisWeek = useThisWeek;
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

	public int getDaysToExp() {
		return daysToExp;
	}

	public void setDaysToExp(int daysToExp) {
		this.daysToExp = daysToExp;
	}

	public boolean getUseThisWeek() {
		return useThisWeek;
	}

	public void setUseThisWeek(boolean useThisWeek) {
		this.useThisWeek = useThisWeek;
	}

    @Override
	public String toString() {
		return "Food Item [id=" + id + ", name=" + name + ", type=" + type + ", daysToExp=" + daysToExp + "]";
	}
}
