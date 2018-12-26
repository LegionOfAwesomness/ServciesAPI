package com.project.shoponline.model;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
// @Data
public class ConsumerData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long consumerDataId;
	private String email;
	private String referralCode;
	private String referredBy;
	@Enumerated(EnumType.STRING)
	private ApplicationStatus applicationStatus;

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

	public ApplicationStatus getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(ApplicationStatus applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	@Override
	public String toString() {
		return "ConsumerData [consumerDataId=" + consumerDataId + ", email=" + email + ", referralCode=" + referralCode
				+ ", referredBy=" + referredBy + ", applicationStatus=" + applicationStatus + "]";
	}

}
