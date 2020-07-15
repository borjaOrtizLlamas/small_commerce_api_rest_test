package com.tfm.dto;

import java.io.Serializable;


public class Product implements Serializable{

	String name;
	String price;
	String description;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "{\"name\":\"" + name + "\", \"price\":\"" + price + "\", \"description\":\""+description+"\"}";
	}


	
}
