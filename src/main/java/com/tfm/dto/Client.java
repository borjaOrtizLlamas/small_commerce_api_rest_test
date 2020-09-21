package com.tfm.dto;	


import java.io.Serializable;
import java.util.List;

public class Client implements Serializable{
	String name; 
	String phone; 
	String surname;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	List<Product> products;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	@Override
	public String toString() {
		return "{\"name\":\"" + name + "\", \"phone\": \""+ phone+"\", \"surname\" : \""+surname+"\",\"products\":[" + products + "]}";
	}

	
	
}
