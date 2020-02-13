package com.infy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dao.LoanDAO;
import com.infy.dao.LoanDAOImpl;
import com.infy.model.Customer;
import com.infy.validator.Validator;

//DONT MODIFY NAME OF CLASS
//DONT ADD/MODIFY/DELETE/COMMENT ANY METHOD
//DONT DELETE/MODIFY INSTANCE VARIABLE(IF PRESENT)
//DONT MODIFY ANNOTATIONS(IF PRESENT)
@Service(value="loanService")
@Transactional
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanDAO loanDAO;

	
	@Override
	public Integer sanctionLoan(Customer customer) throws Exception {
		
		Validator validator=new Validator();
		validator.validate(customer.getLoan());
		
		Integer c=loanDAO.checkLoanAllotment(customer.getCustomerId());
		if(c==0){
			 return loanDAO.sanctionLoan(customer);
		}
		else if(c==-1){
			throw new Exception("Service.CUSTOMER_UNAVAILABLE");
		}
		else{
			throw new Exception("Service.LOAN_ALREADY_TAKEN");
		}
		

	}

	@Override
	public List<Customer> getReportByLoanType(String loanType) throws Exception {
		
		List<Customer> a=loanDAO.getReportByLoanType(loanType);
		if(a.isEmpty())
		{
			throw new Exception("Service.NO_LOAN_FOUND");
		}
		else
		{
			return a;
		}
	}

}
