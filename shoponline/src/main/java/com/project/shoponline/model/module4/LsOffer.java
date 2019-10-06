package com.project.shoponline.model.module4;

import javax.xml.bind.annotation.XmlElement;

public class LsOffer {
	private String alsoName;
	private String commissionTerms;
	private String offerId;
	private String offerName;
	@XmlElement(name = "ns1:alsoName")
	public String getAlsoName() {
		return alsoName;
	}
	public void setAlsoName(String alsoName) {
		this.alsoName = alsoName;
	}
	@XmlElement(name = "ns1:commissionTerms")
	public String getCommissionTerms() {
		return commissionTerms;
	}
	public void setCommissionTerms(String commissionTerms) {
		this.commissionTerms = commissionTerms;
	}
	@XmlElement(name = "ns1:offerId")
	public String getOfferId() {
		return offerId;
	}
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
	@XmlElement(name = "ns1:offerName")
	public String getOfferName() {
		return offerName;
	}
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
	
	
}
