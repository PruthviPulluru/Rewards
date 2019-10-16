package com.customer.rewards.dto;

import java.util.List;

public class CustomerRewardsDTO {
	private Long total;
	private List<MonthDetails> monthDetails;
	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<MonthDetails> getMonthDetails() {
		return monthDetails;
	}
	public void setMonthDetails(List<MonthDetails> monthDetails) {
		this.monthDetails = monthDetails;
	}


	public class MonthDetails {
		private Integer month;
		private Long monthPoints;

		public Integer getMonth() {
			return month;
		}
		public void setMonth(Integer month) {
			this.month = month;
		}
		public Long getMonthPoints() {
			return monthPoints;
		}
		public void setMonthPoints(Long monthPoints) {
			this.monthPoints = monthPoints;
		}
	}
}


