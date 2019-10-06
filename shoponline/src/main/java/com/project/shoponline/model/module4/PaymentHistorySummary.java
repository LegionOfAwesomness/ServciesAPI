package com.project.shoponline.model.module4;

public class PaymentHistorySummary {
	private String PaymentID;
	private String Date;
	private String PaymentType;
	private String CheckNumber;
	private String CurrencyCode;
	private String TotalCommission;
	private String AmountPaid;
	private String PaymentStatus;
	public String getPaymentID() {
		return PaymentID;
	}
	public void setPaymentID(String paymentID) {
		PaymentID = paymentID;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getPaymentType() {
		return PaymentType;
	}
	public void setPaymentType(String paymentType) {
		PaymentType = paymentType;
	}
	public String getCheckNumber() {
		return CheckNumber;
	}
	public void setCheckNumber(String checkNumber) {
		CheckNumber = checkNumber;
	}
	public String getCurrencyCode() {
		return CurrencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		CurrencyCode = currencyCode;
	}
	public String getTotalCommission() {
		return TotalCommission;
	}
	public void setTotalCommission(String totalCommission) {
		TotalCommission = totalCommission;
	}
	public String getAmountPaid() {
		return AmountPaid;
	}
	public void setAmountPaid(String amountPaid) {
		AmountPaid = amountPaid;
	}
	public String getPaymentStatus() {
		return PaymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		PaymentStatus = paymentStatus;
	}
	@Override
	public String toString() {
		return "PaymentHistorySummary [PaymentID=" + PaymentID + ", Date=" + Date + ", PaymentType=" + PaymentType
				+ ", CheckNumber=" + CheckNumber + ", CurrencyCode=" + CurrencyCode + ", TotalCommission="
				+ TotalCommission + ", AmountPaid=" + AmountPaid + ", PaymentStatus=" + PaymentStatus + "]";
	}

}
