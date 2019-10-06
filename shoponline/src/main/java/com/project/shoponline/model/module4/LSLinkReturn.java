package com.project.shoponline.model.module4;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
public class LSLinkReturn {
	private String campaignID;
	private String categoryID;
	private String categoryName;
	private String linkID;
	private String linkName;
	private String mid;
	private String nid;
	private String clickURL;
	private String endDate;
	private String landURL;
	private String showURL;
	private String startDate;
	private String textDisplay;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}
	
	@XmlElement(name = "ns1:campaignID")
	public String getCampaignID() {
		return campaignID;
	}
	public void setCampaignID(String campaignID) {
		this.campaignID = campaignID;
	}
	@XmlElement(name = "ns1:categoryID")
	public String getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}
	@XmlElement(name = "ns1:categoryName")
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@XmlElement(name = "ns1:linkID")
	public String getLinkID() {
		return linkID;
	}
	public void setLinkID(String linkID) {
		this.linkID = linkID;
	}
	@XmlElement(name = "ns1:linkName")
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	@XmlElement(name = "ns1:mid")
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	@XmlElement(name = "ns1:nid")
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	@XmlElement(name = "ns1:clickURL")
	public String getClickURL() {
		return clickURL;
	}
	public void setClickURL(String clickURL) {
		this.clickURL = clickURL;
	}
	@XmlElement(name = "ns1:endDate")
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@XmlElement(name = "ns1:landURL")
	public String getLandURL() {
		return landURL;
	}
	public void setLandURL(String landURL) {
		this.landURL = landURL;
	}
	@XmlElement(name = "ns1:showURL")
	public String getShowURL() {
		return showURL;
	}
	public void setShowURL(String showURL) {
		this.showURL = showURL;
	}
	@XmlElement(name = "ns1:startDate")
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	@XmlElement(name = "ns1:textDisplay")
	public String getTextDisplay() {
		return textDisplay;
	}
	public void setTextDisplay(String textDisplay) {
		this.textDisplay = textDisplay;
	}
}
