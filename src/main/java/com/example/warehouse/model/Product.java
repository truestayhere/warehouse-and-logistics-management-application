package com.example.warehouse.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 30)
	private String productNumber;
	
	@NotNull
	private String productName;
	
	private double weight;
	
	private int quantity;
	
	private double cost;
	
	@ManyToOne
	@JoinColumn(name = "categoryid")
	@JsonManagedReference
	private Category category;
	 
	
	public Product( ) {}
	
	
	public Product(String productNumber, String productName, Category category, double weight, int quantity, double cost) {
		super();
		this.productNumber = productNumber;
		this.productName = productName;
		this.category = category;
		this.weight = weight;
		this.quantity = quantity;
		this.cost = cost;
	}
	


	public String getProductNumber() {
		return productNumber;
	}


	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}
	

	public double getWeight() {
		return weight;
	}


	public void setWeight(double weight) {
		this.weight = weight;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getCost() {
		return cost;
	}


	public void setCost(double cost) {
		this.cost = cost;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", productNumber=" + productNumber + ", productName=" + productName + ", weight="
				+ weight + ", quantity=" + quantity + ", cost=" + cost + ", category=" + category + "]";
	}

	

}
