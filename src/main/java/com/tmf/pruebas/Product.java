package com.tmf.pruebas;

import java.io.Serializable;


public class Product implements Serializable{

	String name;
	String precio;
	String description;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "{\"name\":\"" + name + "\", \"precio\":\"" + precio + "\", \"description\":\""+description+"\"}";
	}


	
}
