package com.springboot_buildingblocks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot_buildingblocks.entities.Order;

@Repository // Chapter 55
public interface OrderRepository extends JpaRepository<Order, Long>{
	
}
