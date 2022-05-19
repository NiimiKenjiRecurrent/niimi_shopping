package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	List<Account> findByEmail(String email);
}
