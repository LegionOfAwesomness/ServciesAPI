package com.project.shoponline.model.module4;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "couponfeed")
public class Couponfeed {
	private long totalMatches;
	private String totalPages;
	private String pageNumberRequested;
	private List<Link> lslinks;
	
	@XmlElement(name = "TotalMatches")
	public long getTotalMatches() {
		return totalMatches;
	}
	public void setTotalMatches(long totalMatches) {
		this.totalMatches = totalMatches;
	}
	@XmlElement(name = "TotalPages")
	public String getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(String totalPages) {
		this.totalPages = totalPages;
	}
	@XmlElement(name = "PageNumberRequested")
	public String getPageNumberRequested() {
		return pageNumberRequested;
	}
	public void setPageNumberRequested(String pageNumberRequested) {
		this.pageNumberRequested = pageNumberRequested;
	}
	@XmlElement(name = "link")
	public List<Link> getLslinks() {
		return lslinks;
	}
	public void setLslinks(List<Link> lslinks) {
		this.lslinks = lslinks;
	}
	@Override
	public String toString() {
		return "Couponfeed [totalMatches=" + totalMatches + ", totalPages=" + totalPages + ", pageNumberRequested="
				+ pageNumberRequested + ", lslinks=" + lslinks + "]";
	}
	
	
}
