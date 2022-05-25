package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.example.demo.OrderHis;

@Entity
@Table(name="order_detail")
@NamedNativeQuery(name = "OrderDetail.niimi", 
query = "select d.item_id, d.quantity, o.ordered_date "
		+ "from ordered o inner join order_detail d "
		+ "on o.id = d.ordered_id "
		+ "where o.user_id = ?1", resultClass = OrderHis.class)
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="ordered_id")
	private Integer orderedId;

	@Column(name="item_id")
	private Integer itemId;

	@Column(name="quantity")
	private Integer quantity;

	public OrderDetail() {
		super();
	}

	public OrderDetail(Integer orderedId, Integer itemId, Integer quantity) {
		super();
		this.orderedId = orderedId;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	public OrderDetail(Integer id, Integer orderedId, Integer itemId, Integer quantity) {
		super();
		this.id = id;
		this.orderedId = orderedId;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderedId() {
		return orderedId;
	}

	public void setOrderedId(Integer orderedId) {
		this.orderedId = orderedId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
