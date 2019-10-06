package com.project.shoponline.model.module1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.shoponline.model.module2.Commission2;
import com.project.shoponline.model.module2.Reward;

@Entity
@JsonIgnoreProperties("consumer")
public class ConsumerData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long consumerDataId;
	private String email;
	private String referralCode;
	private String referredBy;
	@OneToOne
	private ConsumerData referrerConsumerData;
	@Enumerated(EnumType.STRING)
	private MembershipType membershipType;
	@OneToOne(fetch=FetchType.LAZY)
	private Consumer consumer;

	public ConsumerData() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

	public String getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}

	public long getConsumerDataId() {
		return consumerDataId;
	}

	public void setConsumerDataId(long consumerDataId) {
		this.consumerDataId = consumerDataId;
	}


	@Override
	public String toString() {
		return "ConsumerData [consumerDataId=" + consumerDataId + ", email=" + email + ", referralCode=" + referralCode
				+ ", referredBy=" + referredBy + ", applicationStatus=" + membershipType + "]";
	}

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}

	public ConsumerData getReferrerConsumerData() {
		return referrerConsumerData;
	}

	public void setReferrerConsumerData(ConsumerData referrerConsumerData) {
		this.referrerConsumerData = referrerConsumerData;
	}

	public MembershipType getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(MembershipType membershipType) {
		this.membershipType = membershipType;
	}


}
