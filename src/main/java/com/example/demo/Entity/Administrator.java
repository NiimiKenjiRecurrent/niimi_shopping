package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "administrator")
public class Administrator {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "tell")
	private String tell;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "password")
	private String password;

	public Administrator(Integer id, String address, String email, String tell, String name, String password) {
		super();
		this.id = id;
		this.address = address;
		this.email = email;
		this.tell = tell;
		this.name = name;
		this.password = password;
	}

	public Administrator() {
		super();
	}

	public Administrator(String address, String email, String tell, String name, String password) {
		super();
		this.address = address;
		this.email = email;
		this.tell = tell;
		this.name = name;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public String getTell() {
		return tell;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
	
}
