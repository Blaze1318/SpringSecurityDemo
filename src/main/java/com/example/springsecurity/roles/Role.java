package com.example.springsecurity.roles;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table
@Entity
public class Role {
	@Id
	@GeneratedValue(
			strategy = GenerationType.AUTO
			)
	private Long id;
	private String name;
	
	
	public Role(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	public Role() {
		
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
	
	
}
