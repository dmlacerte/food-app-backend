package com.example.food_manager.model;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "expDate")
	private LocalDate expDate;
	@Column(name = "use_this_week")
	private boolean useThisWeek;

    public FoodManager() {

	}

	public FoodManager(String name, String type, boolean useThisWeek, LocalDate expDate) {
		this.name = name;
		this.type = type;
		this.useThisWeek = useThisWeek;
		this.expDate = expDate;
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

	public boolean getUseThisWeek() {
		return useThisWeek;
	}

	public void setUseThisWeek(boolean useThisWeek) {
		this.useThisWeek = useThisWeek;
	}

	public LocalDate getExpDate() {
		return expDate;
	}

	public void setExpDate(LocalDate expDate) {
		this.expDate = expDate;
	}

    @Override
	public String toString() {
		return "Food Item [id=" + id + ", name=" + name + ", type=" + type + ", expDatep=" + expDate + ", useThisWeek=" + useThisWeek + "]";
	}
}
