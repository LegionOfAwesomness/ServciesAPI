package com.project.shoponline.model.module3;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.annotations.GenericGenerator;
@Entity
public class Advertiser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	@Basic(optional = false)
//	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="IdOrGenerated")
//	@GenericGenerator(name="IdOrGenerated",
//	                  strategy="com.project.shoponline.model.module3.UseIdOrGenerate"
//	)
	private Long advertiser_id;
	private String account_status;
	
	private String advertiser_name;
	private String network_rank;
	private String primary_category;
	private String program_url;
	private String seven_day_epc;
	private String three_month_epc;
//	private List<Action> actions;
	@OneToOne(cascade = CascadeType.ALL)
	private Actions actions;
	
	@XmlElement(name = "account-status")
	public String getAccount_status() {
		return account_status;
	}
	public void setAccount_status(String account_status) {
		this.account_status = account_status;
	}
	@XmlElement(name = "advertiser-id")
	public Long getAdvertiser_id() {
		return advertiser_id;
	}
	public void setAdvertiser_id(Long advertiser) {
		this.advertiser_id = advertiser;
	}
	@XmlElement(name = "advertiser-name")
	public String getAdvertiser_name() {
		return advertiser_name;
	}
	public void setAdvertiser_name(String advertiser_name) {
		this.advertiser_name = advertiser_name;
	}
	
	@XmlElement(name = "network-rank")
	public String getNetwork_rank() {
		return network_rank;
	}
	public void setNetwork_rank(String network) {
		this.network_rank = network;
	}
	@XmlElement(name = "primary-category")
	public String getPrimary_category() {
		return primary_category;
	}
	public void setPrimary_category(String primary) {
		this.primary_category = primary;
	}
	@XmlElement(name = "program-url")
	public String getProgram_url() {
		return program_url;
	}
	public void setProgram_url(String program) {
		this.program_url = program;
	}
	
	@XmlElement(name = "seven-day-epc")
	public String getSeven_day_epc() {
		return seven_day_epc;
	}
	public void setSeven_day_epc(String seven) {
		this.seven_day_epc = seven;
	}
	@XmlElement(name = "three-month-epc")
	public String getThree_month_epc() {
		return three_month_epc;
	}
	public void setThree_month_epc(String three) {
		this.three_month_epc = three;
	}
	@XmlElement(name = "actions")
	public Actions getActions() {
		return actions;
	}
	public void setActions(Actions actions) {
		this.actions = actions;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
