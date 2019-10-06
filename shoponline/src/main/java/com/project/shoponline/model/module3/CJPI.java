package com.project.shoponline.model.module3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cj-api")
public class CJPI {
	private Advertisers advertisers;
	private Links links;
	private String total_matched;
	
	@XmlAttribute(name = "total-matched")
	public String getTotal_matched() {
		return total_matched;
	}

	public void setTotal_matched(String total_matched) {
		this.total_matched = total_matched;
	}

	@XmlElement(name = "advertisers")
	public Advertisers getAdvertisers() {
		return advertisers;
	}

	public void setAdvertisers(Advertisers advertisers) {
		this.advertisers = advertisers;
	}
	@XmlElement(name = "links")
	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	@Override
	public String toString() {
		return "CJPI [advertisers=" + advertisers + ", links=" + links + "]";
	}



}