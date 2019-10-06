package com.project.shoponline.model.module4;

import java.util.List;

public class AdvancedReportResponse {
	List<PaymentHistorySummary> PaymentHistorySummary;

	public List<PaymentHistorySummary> getPaymentHistorySummary() {
		return PaymentHistorySummary;
	}

	public void setPaymentHistorySummary(List<PaymentHistorySummary> paymentHistorySummary) {
		PaymentHistorySummary = paymentHistorySummary;
	}
}
