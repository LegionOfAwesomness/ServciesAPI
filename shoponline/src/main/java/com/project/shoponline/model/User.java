package com.project.shoponline.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.GenericGenerator;

@Entity
@JsonIgnoreProperties("consumer")
public class User {

	@Id
	@Column(name="USER_ID")
	@GeneratedValue(generator="gen")
	@GenericGenerator(name="gen", strategy="foreign",parameters=@Parameter(name="property", value="consumer"))
	private long userId;

	private String email;
	private String password;
	private String userName;
	@Enumerated(EnumType.STRING)
	private UserStatus userStatus;
	@Enumerated(EnumType.STRING)
	private Subscription subscription;
	@OneToOne
	@PrimaryKeyJoinColumn
	private Consumer consumer;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
	private List<Role> roles = new ArrayList<>();
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public UserStatus getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}
	public Consumer getConsumer() {
		return consumer;
	}
	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}
	public List<Role> getRole() {
		return roles;
	}
	public void setRole(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", password=" + password + ", userName=" + userName
				+ ", userStatus=" + userStatus + ", subscription=" + subscription + ", consumer=" + consumer
				+ ", roles=" + roles + "]";
	}



}
