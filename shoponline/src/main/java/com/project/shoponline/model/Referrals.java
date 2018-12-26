package com.project.shoponline.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ReferralList")
public class Referrals {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long referralsId;
	private String referralCode;
	@ManyToOne
//	@JoinColumn(name = "consumerDataId")
	private ConsumerData consumerData;

	public Referrals() {
	}

	public long getReferralsId() {
		return referralsId;
	}

	public void setReferralsId(long referralsId) {
		this.referralsId = referralsId;
	}

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

	public ConsumerData getConsumerData() {
		return consumerData;
	}

	public void setConsumerData(ConsumerData consumerData) {
		this.consumerData = consumerData;
	}

}
