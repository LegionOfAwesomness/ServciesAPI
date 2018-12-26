package com.project.shoponline.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	@OneToOne(mappedBy="consumer", cascade = CascadeType.ALL)
	private User user;
	@OneToOne(cascade = CascadeType.ALL)
	private ConsumerData consumerData;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Consumer [consumerId=" + consumerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + ", phoneNumber=" + phoneNumber  + ", password="
				+ ", consumerReferral=" + consumerData + "]";
	}

}
