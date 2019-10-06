package com.project.shoponline.model.module1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.shoponline.model.module2.Account;
import com.project.shoponline.model.module2.Commission2;
//import com.project.shoponline.model.module2.Commission;
//import com.project.shoponline.model.module2.Reward;
import com.project.shoponline.model.module2.Reward;

@Entity
@Table(name = "ConsumerProfile")
public class Consumer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long consumerId;
	private String firstName;
	private String lastName;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	private String phoneNumber;
	@OneToMany(mappedBy="consumer", cascade = CascadeType.ALL)
	private List<User> users = new ArrayList<User>();
	@OneToOne(mappedBy="consumer", cascade = CascadeType.ALL)
	private ConsumerData consumerData;
	@OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL)
	private List<Account> accounts = new ArrayList<Account>();

	public Consumer() {
	}

	public Long getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(Long consumerId) {
		this.consumerId = consumerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	

	public ConsumerData getConsumerData() {
		return consumerData;
	}

	public void setConsumerData(ConsumerData consumerData) {
		this.consumerData = consumerData;
	}


	//
//	public List<Commission> getCommisions() {
//		return commisions;
//	}
//
//	public void setCommisions(List<Commission> commisions) {
//		this.commisions = commisions;
//	}
//
//	public List<Reward> getRewards() {
//		return rewards;
//	}
//
//	public void setRewards(List<Reward> rewards) {
//		this.rewards = rewards;
//	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "Consumer [consumerId=" + consumerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + ", phoneNumber=" + phoneNumber  + ", password="
				+ ", consumerReferral=" + consumerData + "]";
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setCommisions(List<Account> accounts) {
		this.accounts = accounts;
	}
	

}
