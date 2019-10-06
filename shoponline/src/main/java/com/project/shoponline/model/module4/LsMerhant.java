package com.project.shoponline.model.module4;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class LsMerhant {
	private String applicationStatus;
	private String categories;
	private String mid;
	private String name;
	private LsOffer lsOffer;
	@XmlElement(name = "ns1:applicationStatus")
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	@XmlElement(name = "ns1:categories")
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	@XmlElement(name = "ns1:mid")
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	@XmlElement(name = "ns1:name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LsOffer getLsOffer() {
		return lsOffer;
	}
	public void setLsOffer(LsOffer lsOffer) {
		this.lsOffer = lsOffer;
	}
	
	
	
}
