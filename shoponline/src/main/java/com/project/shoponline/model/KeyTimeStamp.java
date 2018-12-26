package com.project.shoponline.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "KeyTimeStamp")
public class KeyTimeStamp {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long KeyTimeStampId;
	private String keyGenerated;
	private LocalDateTime timeStamp;
	private String expirationTime;
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	
	public KeyTimeStamp() {
	}
	public long getKeyTimeStampId() {
		return KeyTimeStampId;
	}
	public void setKeyTimeStampId(long keyTimeStampId) {
		KeyTimeStampId = keyTimeStampId;
	}
	public String getKey() {
		return keyGenerated;
	}
	public void setKey(String keyGenerated) {
		this.keyGenerated = keyGenerated;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(String expirationTime) {
		this.expirationTime = expirationTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "KeyTimeStamp [KeyTimeStampId=" + KeyTimeStampId + ", key=" + keyGenerated + ", timeStamp=" + timeStamp
				+ ", expirationTime=" + expirationTime + ", user=" + user + "]";
	}
	
}
