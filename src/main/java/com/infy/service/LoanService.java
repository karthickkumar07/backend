package com.infy.service;

import java.util.List;

import com.infy.model.Customer;

public interface LoanService {

	public List<Customer> getReportByLoanType(String loanType) throws Exception;

	public Integer sanctionLoan(Customer customer) throws Exception;

}
