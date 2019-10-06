package com.project.shoponline.model.module2;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.shoponline.model.module1.Address;
import com.project.shoponline.model.module1.Consumer;
import com.project.shoponline.model.module1.ConsumerData;
import com.project.shoponline.model.module1.User;

import software.amazon.ion.Decimal;
@Entity
@JsonIgnoreProperties({"consumerData","account"})
public class Commission2 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commissiondId;
	private BigDecimal commissionPercent;
	private int commissionLevel;
	private BigDecimal commissionAmount;
	private LocalDate commissionDate;
	private String commissionStatus;
	@OneToOne(cascade = CascadeType.ALL)
	private Transaction transaction;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_account")
	private Account account;
	public Commission2() {
	}
	public Long getCommissiondId() {
		return commissiondId;
	}
	public void setCommissiondId(Long commissiondId) {
		this.commissiondId = commissiondId;
	}
	public BigDecimal getCommissionPercent() {
		return commissionPercent;
	}
	public void setCommissionPercent(BigDecimal commissionPercent) {
		this.commissionPercent = commissionPercent;
	}
	public int getCommissionLevel() {
		return commissionLevel;
	}
	public void setCommissionLevel(int commissionLevel) {
		this.commissionLevel = commissionLevel;
	}
	public BigDecimal getCommissionAmount() {
		return commissionAmount;
	}
	public void setCommissionAmount(BigDecimal commissionAmount) {
		this.commissionAmount = commissionAmount;
	}
	public LocalDate getCommissionDate() {
		return commissionDate;
	}
	public void setCommissionDate(LocalDate commissionDate) {
		this.commissionDate = commissionDate;
	}
	public String getCommissionStatus() {
		return commissionStatus;
	}
	public void setCommissionStatus(String commissionStatus) {
		this.commissionStatus = commissionStatus;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}

	
}
