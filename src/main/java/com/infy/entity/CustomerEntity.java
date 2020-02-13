package com.infy.entity;

import javax.persistence.*;

import com.infy.model.Loan;

//Strictly follow class diagram
@Entity
@Table(name="Customer")
public class CustomerEntity {
	@Id
	@Column(name="customer_id")
	private Integer customerId;
	@Column(name="name")
	private String customerName;
	@Column(name="mobile_no")
	private Long mobileNo;
	@OneToOne
	@JoinColumn(name="loan_id",unique=true)
	private LoanEntity loan;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public LoanEntity getLoan() {
		return loan;
	}

	public void setLoan(LoanEntity loan) {
		this.loan = loan;
	}
	
}
