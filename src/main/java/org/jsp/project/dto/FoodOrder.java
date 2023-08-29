package org.jsp.project.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class FoodOrder {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false)
	private String location;
	@CreationTimestamp
	private LocalDateTime orderedTime;
	@UpdateTimestamp
	private LocalDateTime deliveryTime;
	@ManyToMany(cascade = CascadeType.ALL, mappedBy="foods")
	private List<User> users;
	public List<User> getFoods() {
		return users;
	}
	public void setFoods(List<User>users) {
		this.users = users;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public LocalDateTime getOrderedTime() {
		return orderedTime;
	}
	public void setOrderedTime(LocalDateTime orderedTime) {
		this.orderedTime = orderedTime;
	}
	public LocalDateTime getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(LocalDateTime deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	
}
