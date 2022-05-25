package com.example.demo.Entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ordered")
public class Order {
	private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer code;
	
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="ordered_date")
	private LocalDateTime orderedDate;
	
	@Column(name="total_price")
	private Integer totalPrice;

	public Order() {
		super();
	}
	
	public Order(Integer code, Integer userId, Integer totalPrice) {
		super();
		this.code = code;
		this.userId = userId;
		this.orderedDate = LocalDateTime.now();
		this.totalPrice = totalPrice;
	}
	

	public Order(Integer userId, Integer totalPrice) {
		super();
		this.userId = userId;
		this.orderedDate = LocalDateTime.now();
		this.totalPrice = totalPrice;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOrderedDate() {
		return orderedDate.format(fmt);
	}


	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
