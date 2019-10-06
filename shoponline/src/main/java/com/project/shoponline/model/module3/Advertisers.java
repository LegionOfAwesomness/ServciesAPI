package com.project.shoponline.model.module3;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
public class Advertisers {
	private String page_number;
	private String total_matched;
	private String records_returned;
	private List<Advertiser> advertiserList= new ArrayList<Advertiser>();

	@XmlElement(name = "page-number")
	public String getPage_number() {
		return page_number;
	}

	public void setPage_number(String page_number) {
		this.page_number = page_number;
	}

	@XmlElement(name = "total-matched")
	public String getTotal_matched() {
		return total_matched;
	}

	public void setTotal_matched(String total_matched) {
		this.total_matched = total_matched;
	}

	@XmlElement(name = "records-returned")
	public String getRecords_returned() {
		return records_returned;
	}

	public void setRecords_returned(String records_returned) {
		this.records_returned = records_returned;
	}

	@XmlElement(name = "advertiser")
	public List<Advertiser> getAdvertiserList() {
		return advertiserList;
	}

	public void setAdvertiserList(List<Advertiser> advertiserList) {
		this.advertiserList = advertiserList;
	}

	@Override
	public String toString() {
		return "Advertisers [page_number=" + page_number + ", total_matched=" + total_matched + ", records_returned="
				+ records_returned + ", advertiserList=" + advertiserList + "]";
	}


}
