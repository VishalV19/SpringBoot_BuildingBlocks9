package com.springboot_buildingblocks.dtos;

import java.util.List;

import com.springboot_buildingblocks.entities.Order;

public class UserMmDto {
	
	private Long id;
	private String firstname;
	private String username;
	
	private List<Order> orders;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
	

}
