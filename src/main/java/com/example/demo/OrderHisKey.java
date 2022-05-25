package com.example.demo;

import java.io.Serializable;

public class OrderHisKey implements Serializable  {
	private int itemId;
	private int orderId;
	public OrderHisKey() {
		super();
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
}
