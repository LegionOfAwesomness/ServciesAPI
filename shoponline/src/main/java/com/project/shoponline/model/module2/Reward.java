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
import com.project.shoponline.model.module1.Consumer;
import com.project.shoponline.model.module1.ConsumerData;
import com.project.shoponline.model.module1.User;

import software.amazon.ion.Decimal;
@Entity
@JsonIgnoreProperties("consumerData")
public class Reward {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rewardId;
	private Decimal rewardPercent;
	private BigDecimal rewardAmount;
	private LocalDate rewardDate;
	private String rewardStatus;
	@OneToOne(cascade = CascadeType.ALL)
	private Transaction transaction;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_account")
	private Account account;
	
	public Reward() {
	}
	public Long getRewardId() {
		return rewardId;
	}
	public void setRewardId(Long rewardId) {
		this.rewardId = rewardId;
	}
	public Decimal getRewardPercent() {
		return rewardPercent;
	}
	public void setRewardPercent(Decimal rewardPercent) {
		this.rewardPercent = rewardPercent;
	}
	public BigDecimal getRewardAmount() {
		return rewardAmount;
	}
	public void setRewardAmount(BigDecimal rewardAmount) {
		this.rewardAmount = rewardAmount;
	}
	public LocalDate getRewardDate() {
		return rewardDate;
	}
	public void setRewardDate(LocalDate rewardDate) {
		this.rewardDate = rewardDate;
	}
	public String getRewardStatus() {
		return rewardStatus;
	}
	public void setRewardStatus(String rewardStatus) {
		this.rewardStatus = rewardStatus;
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
