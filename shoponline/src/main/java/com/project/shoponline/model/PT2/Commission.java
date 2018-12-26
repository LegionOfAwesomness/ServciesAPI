package com.project.shoponline.model.PT2;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.project.shoponline.model.Address;
import com.project.shoponline.model.ConsumerData;
import com.project.shoponline.model.User;

public class Commission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commissionId;
//	private String firstName;
//	private String lastName;
//	@OneToOne(cascade = CascadeType.ALL)
//	private Address address;
//	private String phoneNumber;
	@ManyToOne
	@JoinColumn(name = "consumerDataId")
	private ConsumerData consumerData;
}
