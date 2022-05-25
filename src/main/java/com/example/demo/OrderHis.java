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
	private String itemname;
	private int price;
	private String image;
	
	public OrderHis() {
		super();
	}
	public OrderHis(int itemId, int quantity, LocalDateTime orderedDate) {
		super();
		this.itemId = itemId;
		this.quantity = quantity;
		this.orderedDate = orderedDate;
	}
	
	public OrderHis(int itemId, int quantity, LocalDateTime orderedDate, String itemname, int price, String image) {
		super();
		this.itemId = itemId;
		this.quantity = quantity;
		this.orderedDate = orderedDate;
		this.itemname = itemname;
		this.price = price;
		this.image = image;
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
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImg() {
		return image;
	}
	public void setImg(String img) {
		this.image = img;
	}

}
