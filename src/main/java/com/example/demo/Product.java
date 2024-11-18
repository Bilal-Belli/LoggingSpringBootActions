package com.example.demo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    private Long id;
    private String name;
    private double price;
    private LocalDate expirationDate;
	public Product(Long id, String name, double price, LocalDate expirationDate) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.expirationDate = expirationDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	public Product() {
		super();
	}
}