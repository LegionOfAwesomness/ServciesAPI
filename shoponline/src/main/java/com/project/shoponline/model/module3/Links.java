package com.project.shoponline.model.module3;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Links {
	private List<Link> linkList = new ArrayList<Link>();
	private String total_matched;
	private String pageNumber;
	
	@XmlAttribute(name = "page-number")
	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	@XmlAttribute(name = "total-matched")
	public String getTotal_matched() {
		return total_matched;
	}

	public void setTotal_matched(String total_matched) {
		this.total_matched = total_matched;
	}

	@XmlElement(name = "link")
	public List<Link> getLinkList() {
		return linkList;
	}

	public void setLinkList(List<Link> linkList) {
		this.linkList = linkList;
	}

	@Override
	public String toString() {
		return "Links [linkList=" + linkList + ", total_matched=" + total_matched + ", pageNumber=" + pageNumber + "]";
	}



}
