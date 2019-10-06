package com.project.shoponline.model.module4;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Link {

	private List<String> lscategories;
	private List<String> lspromotiontypes;
	private String offerdescription;
	private String offerstartdate;
	private String offerenddate;
	private String couponcode;
	private String couponrestriction;
	private String clickurl;
	private String impressionpixel;
	private String advertiserid;
	private String advertisername;
	private String networkid;
	
	@XmlElement(name = "categories")
	public List<String> getLscategories() {
		return lscategories;
	}
	public void setLscategories(List<String> lscategories) {
		this.lscategories = lscategories;
	}
	@XmlElement(name = "promotiontypes")
	public List<String> getLspromotiontypes() {
		return lspromotiontypes;
	}
	public void setLspromotiontypes(List<String> lspromotiontypes) {
		this.lspromotiontypes = lspromotiontypes;
	}
	@XmlElement(name = "offerdescription")
	public String getOfferdescription() {
		return offerdescription;
	}
	public void setOfferdescription(String offerdescription) {
		this.offerdescription = offerdescription;
	}
	@XmlElement(name = "Offerstartdate")
	public String getOfferstartdate() {
		return offerstartdate;
	}
	public void setOfferstartdate(String offerstartdate) {
		this.offerstartdate = offerstartdate;
	}
	@XmlElement(name = "Offerenddate")
	public String getOfferenddate() {
		return offerenddate;
	}
	public void setOfferenddate(String offerenddate) {
		this.offerenddate = offerenddate;
	}
	@XmlElement(name = "couponcode")
	public String getCouponcode() {
		return couponcode;
	}
	public void setCouponcode(String couponcode) {
		this.couponcode = couponcode;
	}
	@XmlElement(name = "couponrestriction")
	public String getCouponrestriction() {
		return couponrestriction;
	}
	public void setCouponrestriction(String couponrestriction) {
		this.couponrestriction = couponrestriction;
	}
	@XmlElement(name = "clickurl")
	public String getClickurl() {
		return clickurl;
	}
	public void setClickurl(String clickurl) {
		this.clickurl = clickurl;
	}
	@XmlElement(name = "impressionpixel")
	public String getImpressionpixel() {
		return impressionpixel;
	}
	public void setImpressionpixel(String impressionpixel) {
		this.impressionpixel = impressionpixel;
	}
	@XmlElement(name = "advertiserid")
	public String getAdvertiserid() {
		return advertiserid;
	}
	public void setAdvertiserid(String advertiserid) {
		this.advertiserid = advertiserid;
	}
	@XmlElement(name = "advertisername")
	public String getAdvertisername() {
		return advertisername;
	}
	public void setAdvertisername(String advertisername) {
		this.advertisername = advertisername;
	}
	@XmlElement(name = "network id")
	public String getNetworkid() {
		return networkid;
	}
	public void setNetworkid(String networkid) {
		this.networkid = networkid;
	}
	@Override
	public String toString() {
		return "Link [lscategories=" + lscategories + ", lspromotiontypes=" + lspromotiontypes + ", offerdescription="
				+ offerdescription + ", offerstartdate=" + offerstartdate + ", offerenddate=" + offerenddate
				+ ", couponcode=" + couponcode + ", couponrestriction=" + couponrestriction + ", clickurl=" + clickurl
				+ ", impressionpixel=" + impressionpixel + ", advertiserid=" + advertiserid + ", advertisername="
				+ advertisername + ", networkid=" + networkid + "]";
	}
	
	
	
}
