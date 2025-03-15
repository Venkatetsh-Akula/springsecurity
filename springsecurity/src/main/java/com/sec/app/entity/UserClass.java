package com.sec.app.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UserClass {
	@Id
	@GeneratedValue
	private UUID uuid; //it generate uniqe id
	private String name;
	private Integer age;
	private String email;
	private String password;
	private String role;
	public UserClass() {
		super();
	}
	public UserClass(UUID uuid, String name, Integer age, String email, String password, String role) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.age = age;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "UserClass [uuid=" + uuid + ", name=" + name + ", age=" + age + ", email=" + email + ", password="
				+ password + ", role=" + role + "]";
	}
}
