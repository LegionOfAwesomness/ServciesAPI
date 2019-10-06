package com.project.shoponline.model.module4;

import java.util.List;

public class PublisherCommissions {
	private int count;
	private boolean payloadComplete;
	private List<Records> records;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public boolean getPayloadComplete() {
		return payloadComplete;
	}
	public void setPayloadComplete(boolean payloadComplete) {
		this.payloadComplete = payloadComplete;
	}
	public List<Records> getRecords() {
		return records;
	}
	public void setRecords(List<Records> records) {
		this.records = records;
	}
	@Override
	public String toString() {
		return "PublisherCommissions [count=" + count + ", payloadComplete=" + payloadComplete + ", records=" + records
				+ "]";
	}
	
}
