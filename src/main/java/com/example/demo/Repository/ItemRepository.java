package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.Item;

public interface ItemRepository  extends JpaRepository<Item, Integer>{
	List<Item> findALLByOrderByIdAsc();
	List<Item> findAllByNameContaining(@Param("namePrefix") String str);
}
