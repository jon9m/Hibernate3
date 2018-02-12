package com.mmks.dto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="HATCH")
public class Hatchback extends Vehicle {
	private String model;
	private String capacity;

	public Hatchback(String model, String capacity) {
		super();
		this.model = model;
		this.capacity = capacity;
	}

	public Hatchback() {
		super();
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return getClass().getName() + " {\n\tmodel: " + model + "\n\tcapacity: " + capacity + "\n}";
	}

}
