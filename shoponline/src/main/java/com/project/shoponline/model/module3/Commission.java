package com.project.shoponline.model.module3;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
public class Commission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String default_value;
//	@OneToOne(cascade = CascadeType.ALL)
//	private Default_Object default_object;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@XmlElement(name = "default")
	public String getDefault_value() {
		return default_value;
	}
	public void setDefault_value(String default_value) {
		this.default_value = default_value;
	}
//	@XmlElement(name = "default")
//	public Default_Object getDefault_object() {
//		return default_object;
//	}
//	public void setDefault_object(Default_Object default_object) {
//		this.default_object = default_object;
//	}
	
	
}
