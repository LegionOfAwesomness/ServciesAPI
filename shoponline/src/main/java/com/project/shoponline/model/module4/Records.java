package com.project.shoponline.model.module4;

import java.util.List;

public class Records {
	private String actionTrackerName;
	private String websiteName;
	private String advertiserName;
	private String commissionId;
	private String postingDate;
	private String pubCommissionAmountUsd;
	private List<Items> items;
	public String getActionTrackerName() {
		return actionTrackerName;
	}
	public void setActionTrackerName(String actionTrackerName) {
		this.actionTrackerName = actionTrackerName;
	}
	public String getWebsiteName() {
		return websiteName;
	}
	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}
	public String getAdvertiserName() {
		return advertiserName;
	}
	public void setAdvertiserName(String advertiserName) {
		this.advertiserName = advertiserName;
	}
	public String getCommissionId() {
		return commissionId;
	}
	public void setCommissionId(String commissionId) {
		this.commissionId = commissionId;
	}
	public String getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}
	public String getPubCommissionAmountUsd() {
		return pubCommissionAmountUsd;
	}
	public void setPubCommissionAmountUsd(String pubCommissionAmountUsd) {
		this.pubCommissionAmountUsd = pubCommissionAmountUsd;
	}
	public List<Items> getItems() {
		return items;
	}
	public void setItems(List<Items> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "Records [actionTrackerName=" + actionTrackerName + ", websiteName=" + websiteName + ", advertiserName="
				+ advertiserName + ", commissionId=" + commissionId + ", postingDate=" + postingDate
				+ ", pubCommissionAmountUsd=" + pubCommissionAmountUsd + ", items=" + items + "]";
	}
	
	
}
