package com.infy.dao;

import java.util.List;

import com.infy.model.Customer;

public interface LoanDAO {
	
	public List<Customer> getReportByLoanType(String loanType) throws Exception;
	
	public Integer checkLoanAllotment(Integer customerId) throws Exception;
	
	public Integer sanctionLoan(Customer customer) throws Exception;
	
	
}
