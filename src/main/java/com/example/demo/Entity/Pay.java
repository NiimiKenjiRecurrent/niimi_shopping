package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pay")
public class Pay {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="credit_no")
	private String creditNo;
	
	@Column(name="credit_security")
	private Integer creditSecurity;

	public Pay() {
		super();
	}
	
	public Pay(Integer id, Integer userId, String creditNo, Integer creditSecurity) {
		super();
		this.id = id;
		this.userId = userId;
		this.creditNo = creditNo;
		this.creditSecurity = creditSecurity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCreditNo() {
		return creditNo;
	}

	public void setCreditNo(String creditNo) {
		this.creditNo = creditNo;
	}

	public Integer getCreditSecurity() {
		return creditSecurity;
	}

	public void setCreditSecurity(Integer creditSecurity) {
		this.creditSecurity = creditSecurity;
	}
	
}
