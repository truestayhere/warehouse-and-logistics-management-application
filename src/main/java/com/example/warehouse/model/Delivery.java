package com.example.warehouse.model;

import javax.validation.constraints.Size;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity
public class Delivery {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long deliveryid;
	
	private String deliveryNumber;
	
	private String date;
	
	private String time;
	
	private int quantity;
	
	private double totalWeight;
	
	private double cost;
	
	private String status;
	
	
	public Delivery() {}


	public Delivery(String deliveryNumber, String date, String time, int quantity, double totalWeight, double cost, String status) {
		super();
		this.deliveryNumber = deliveryNumber;
		this.date = date;
		this.time = time;
		this.totalWeight = totalWeight;
		this.quantity = quantity;
		this.cost = cost;
		this.status = status;
	}


	public String getDeliveryNumber() {
		return deliveryNumber;
	}


	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public double getTotalWeight() {
		return totalWeight;
	}


	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
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


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	

	public Long getDeliveryid() {
		return deliveryid;
	}


	public void setDeliveryid(Long deliveryid) {
		this.deliveryid = deliveryid;
	}


	@Override
	public String toString() {
		return "Delivery [deliveryid=" + deliveryid + ", deliveryNumber=" + deliveryNumber + ", date=" + date + ", time=" + time
				+ ", quantity=" + quantity + ", totalWeight=" + totalWeight + ", cost="
				+ cost + ", status=" + status + "]";
	}


	
	

}
