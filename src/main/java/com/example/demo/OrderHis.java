package com.example.demo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderHis {
	
	@Id
	private int itemId;
	private int quantity;
	private LocalDateTime orderedDate;
	
	public OrderHis() {
		super();
	}
	public OrderHis(int itemId, int quantity, LocalDateTime orderedDate) {
		super();
		this.itemId = itemId;
		this.quantity = quantity;
		this.orderedDate = orderedDate;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public LocalDateTime getOrderedDate() {
		return orderedDate;
	}
	public void setOrderedDate(LocalDateTime orderedDate) {
		this.orderedDate = orderedDate;
	}
	
}
