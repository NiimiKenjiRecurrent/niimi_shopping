package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	Account findByEmail(String email);
	Account findById(int id);
	void deleteByEmail(String email);
	List<Account> findALLByOrderByIdAsc();
	List<Account> findAllByEmail(String email);
}
