package com.project.shoponline.model.module3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.annotations.Type;

@Entity
public class Link {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
//		private Long advertiser_id;
	private String advertiserId;
	private String click_commission;
	private String creative_height;
	private String creative_width;
	private String lead_commission;
	@Lob
	@Column( length = 100000 )
	private String link_code_html;
	@Lob
	@Column( length = 100000 )
	private String link_code_javascript;
	private String link_destination;
	@Lob
	@Column( length = 100000 )
	private String description;
	private String link_id;
	private String link_name;
	private String linkType;
	private String advertiser_name;
	private String performance_incentive;
	private String promotion_type;
	private String promotion_start_date;
	private String promotion_end_date;
	private String relationship_status;
	private String sale_commission;
	private String seven_day_epc;
	private String three_month_epc;	
	private String clickURL;
	private String category;
	private String language;	
	private String coupon_code;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@XmlElement(name = "advertiser-id")
	public String getAdvertiserId() {
		return advertiserId;
	}
	
	public void setAdvertiserId(String advertiser_id) {
		this.advertiserId = advertiser_id;
	}
	@XmlElement(name = "click-commission")
	public String getClick_commission() {
		return click_commission;
	}
	public void setClick_commission(String click_commission) {
		this.click_commission = click_commission;
	}
	@XmlElement(name = "creative-height")
	public String getCreative_height() {
		return creative_height;
	}
	public void setCreative_height(String creative_height) {
		this.creative_height = creative_height;
	}
	@XmlElement(name = "creative-width")
	public String getCreative_width() {
		return creative_width;
	}
	public void setCreative_width(String creative_width) {
		this.creative_width = creative_width;
	}
	@XmlElement(name = "lead-commission")
	public String getLead_commission() {
		return lead_commission;
	}
	public void setLead_commission(String lead_commission) {
		this.lead_commission = lead_commission;
	}
	@XmlElement(name = "link-code-html")
	public String getLink_code_html() {
		return link_code_html;
	}
	public void setLink_code_html(String link_code_html) {
		this.link_code_html = link_code_html;
	}
	@XmlElement(name = "link-code-javascript")
	public String getLink_code_javascript() {
		return link_code_javascript;
	}
	public void setLink_code_javascript(String link_code_javascript) {
		this.link_code_javascript = link_code_javascript;
	}
	@XmlElement(name = "link-destination")
	public String getLink_destination() {
		return link_destination;
	}
	public void setLink_destination(String link_destination) {
		this.link_destination = link_destination;
	}
	@XmlElement(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@XmlElement(name = "link-id")
	public String getLink_id() {
		return link_id;
	}
	public void setLink_id(String link_id) {
		this.link_id = link_id;
	}
	@XmlElement(name = "link-name")
	public String getLink_name() {
		return link_name;
	}
	public void setLink_name(String link_name) {
		this.link_name = link_name;
	}
	@XmlElement(name = "link-type")
	public String getLink_type() {
		return linkType;
	}
	public void setLink_type(String link_type) {
		this.linkType = link_type;
	}
	@XmlElement(name = "advertiser-name")
	public String getAdvertiser_name() {
		return advertiser_name;
	}
	public void setAdvertiser_name(String advertiser_name) {
		this.advertiser_name = advertiser_name;
	}
	@XmlElement(name = "performance-incentive")
	public String getPerformance_incentive() {
		return performance_incentive;
	}
	public void setPerformance_incentive(String performance_incentive) {
		this.performance_incentive = performance_incentive;
	}
	@XmlElement(name = "promotion-type")
	public String getPromotion_type() {
		return promotion_type;
	}
	public void setPromotion_type(String promotion_type) {
		this.promotion_type = promotion_type;
	}
	@XmlElement(name = "promotion-start-date")
	public String getPromotion_start_date() {
		return promotion_start_date;
	}
	public void setPromotion_start_date(String promotion_start_date) {
		this.promotion_start_date = promotion_start_date;
	}
	@XmlElement(name = "promotion-end-date")
	public String getPromotion_end_date() {
		return promotion_end_date;
	}
	public void setPromotion_end_date(String promotion_end_date) {
		this.promotion_end_date = promotion_end_date;
	}
	@XmlElement(name = "relationship-status")
	public String getRelationship_status() {
		return relationship_status;
	}
	public void setRelationship_status(String relationship_status) {
		this.relationship_status = relationship_status;
	}
	@XmlElement(name = "sale-commission")
	public String getSale_commission() {
		return sale_commission;
	}
	public void setSale_commission(String sale_commission) {
		this.sale_commission = sale_commission;
	}
	@XmlElement(name = "seven-day-epc")
	public String getSeven_day_epc() {
		return seven_day_epc;
	}
	public void setSeven_day_epc(String seven_day_epc) {
		this.seven_day_epc = seven_day_epc;
	}
	@XmlElement(name = "three-month-epc")
	public String getThree_month_epc() {
		return three_month_epc;
	}
	public void setThree_month_epc(String three_month_epc) {
		this.three_month_epc = three_month_epc;
	}
	@XmlElement(name = "clickURL")
	public String getClickURL() {
		return clickURL;
	}
	public void setClickURL(String clickURL) {
		this.clickURL = clickURL;
	}
	@XmlElement(name = "category")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@XmlElement(name = "language")	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@XmlElement(name = "coupon-code")
	public String getCoupon_code() {
		return coupon_code;
	}
	public void setCoupon_code(String coupon_code) {
		this.coupon_code = coupon_code;
	}

	@Override
	public String toString() {
		return "Link [advertiser_id=" + advertiserId + ", click_commission=" + click_commission + ", creative_height="
				+ creative_height + ", creative_width=" + creative_width + ", lead_commission=" + lead_commission
				+ ", link_code_html=" + link_code_html + ", link_code_javascript=" + link_code_javascript
				+ ", link_destination=" + link_destination + ", description=" + description + ", link_id=" + link_id
				+ ", link_name=" + link_name + ", link_type=" + linkType + ", advertiser_name=" + advertiser_name
				+ ", performance_incentive=" + performance_incentive + ", promotion_type=" + promotion_type
				+ ", promotion_start_date=" + promotion_start_date + ", promotion_end_date=" + promotion_end_date
				+ ", relationship_status=" + relationship_status + ", sale_commission=" + sale_commission
				+ ", seven_day_epc=" + seven_day_epc + ", three_month_epc=" + three_month_epc + ", clickURL=" + clickURL
				+ ", category=" + category + ", language=" + language + ", coupon_code=" + coupon_code + "]";
	}	
	
}
