package com.project.shoponline.model.module2;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.shoponline.model.module1.Consumer;
@Entity
@JsonIgnoreProperties("consumer")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountNumber;
	private BigDecimal balance;
	private String accountType;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Transaction> transactions =  new ArrayList<>();
	@ManyToOne
	private Consumer consumer;
	@OneToMany(mappedBy = "account")
	private List<Commission2> commisions = new ArrayList<Commission2>();
	@OneToMany(mappedBy = "account")
	private List<Reward> rewards = new ArrayList<Reward>();
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public Consumer getConsumer() {
		return consumer;
	}
	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}
	public List<Commission2> getCommisions() {
		return commisions;
	}
	public void setCommisions(List<Commission2> commisions) {
		this.commisions = commisions;
	}
	public List<Reward> getRewards() {
		return rewards;
	}
	public void setRewards(List<Reward> rewards) {
		this.rewards = rewards;
	}
	
}
