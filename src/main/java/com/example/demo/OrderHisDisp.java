package com.example.demo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderHisDisp {
	
	private Integer orderId;
	private Integer totalPrice;
	private LocalDateTime orderedDate;
	private List<OrderHis> orderhiss = new ArrayList<OrderHis>();
	public OrderHisDisp() {
		super();
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderid) {
		this.orderId = orderid;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalprice) {
		this.totalPrice = totalprice;
	}
	public LocalDateTime getOrderedDate() {
		return orderedDate;
	}
	public void setOrderedDate(LocalDateTime orderedDate) {
		this.orderedDate = orderedDate;
	}
	public List<OrderHis> getOrderhiss() {
		return orderhiss;
	}
	public void setOrderhiss(List<OrderHis> orderhiss) {
		this.orderhiss = orderhiss;
	}
	
	
	
}
