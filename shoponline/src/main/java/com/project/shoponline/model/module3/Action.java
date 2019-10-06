package com.project.shoponline.model.module3;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
public class Action {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long action_id;
	private String name;
	@OneToOne(cascade = CascadeType.ALL)
	private Commission commission;
	private String id;
	
	@XmlElement(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement(name = "commission")
	public Commission getCommission() {
		return commission;
	}
	public void setCommission(Commission commission) {
		this.commission = commission;
	}
	@XmlElement(name = "id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getAction_id() {
		return action_id;
	}
	public void setAction_id(Long action_id) {
		this.action_id = action_id;
	}
	
}
