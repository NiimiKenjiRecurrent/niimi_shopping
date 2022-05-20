package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Pay;

public interface PayRepository extends JpaRepository<Pay, Integer>{
	List<Pay> findByUserId(Integer userId);
}
