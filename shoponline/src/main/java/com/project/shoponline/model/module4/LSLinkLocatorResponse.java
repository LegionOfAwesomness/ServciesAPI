package com.project.shoponline.model.module4;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "ns1:getTextLinksResponse")
public class LSLinkLocatorResponse {

	private List<LSLinkReturn> lSLinkReturn;

	@XmlElement(name = "ns1:return")
	public List<LSLinkReturn> getlSLinkReturn() {
		return lSLinkReturn;
	}

	public void setlSLinkReturn(List<LSLinkReturn> lSLinkReturn) {
		this.lSLinkReturn = lSLinkReturn;
	}
	
}

