package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "items")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "stock")
	private Integer stock;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "delivery_days")
	private Integer deliveryDays;

	@Transient
	private Integer quantity;

	public Item(Integer id, Integer price, Integer stock, String image, String name, Integer deliveryDays,
			Integer quantity) {
		super();
		this.id = id;
		this.price = price;
		this.stock = stock;
		this.image = image;
		this.name = name;
		this.deliveryDays = deliveryDays;
		this.quantity = quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Item() {
		super();
	}
	
	public Item(Integer id, Integer price, Integer stock, String image, String name, Integer deliveryDays) {
		super();
		this.id = id;
		this.price = price;
		this.stock = stock;
		this.image = image;
		this.name = name;
		this.deliveryDays = deliveryDays;
		quantity=1;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDeliveryDays() {
		return deliveryDays;
	}

	public void setDeliveryDays(Integer deliveryDays) {
		this.deliveryDays = deliveryDays;
	}

	public Integer getQuantity() {
		// TODO 自動生成されたメソッド・スタブ
		return quantity;
	}
}
