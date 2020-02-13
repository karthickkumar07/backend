package com.infy.dao;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.entity.CustomerEntity;
import com.infy.entity.LoanEntity;
import com.infy.model.Customer;
import com.infy.model.Loan;

//DONT MODIFY NAME OF CLASS
//DONT ADD/MODIFY/DELETE/COMMENT ANY METHOD
//DONT DELETE/MODIFY INSTANCE VARIABLE(IF PRESENT)
//DONT MODIFY ANNOTATIONS(IF PRESENT)
@Repository(value="loanDAO")
public class LoanDAOImpl implements LoanDAO {

	@PersistenceContext
	private EntityManager entityManager;

	
	public List<Customer> getReportByLoanType(String loanType) throws Exception {
		String query = "select s from CustomerEntity s";
		Query a=entityManager.createQuery(query);
		List<CustomerEntity> result=a.getResultList();
		List<Customer> cl=new ArrayList<Customer>();
		for(CustomerEntity ce:result){
			if(ce.getLoan().getLoanType().toLowerCase().equals(loanType.toLowerCase())){
			Customer c =new Customer();
			c.setCustomerId(ce.getCustomerId());
			c.setCustomerName(ce.getCustomerName());
			c.setMobileNo(ce.getMobileNo());
			Double emi=0.0;
			Double la=ce.getLoan().getLoanAmount();
			Integer t=ce.getLoan().getTerm();
			Double ir=ce.getLoan().getInterestRate();
			emi=Math.ceil(((la+(la*t*ir))/100)/(t*12));
			c.setEmi(emi);
			Loan l=new Loan();
			LoanEntity le=ce.getLoan();
			l.setLoanAmount(le.getLoanAmount());
			l.setLoanId(le.getLoanId());
			l.setLoanType(le.getLoanType());
			l.setTerm(le.getTerm());
			l.setInterestRate(le.getInterestRate());
			c.setLoan(l);
			cl.add(c);
			
		}
		}
		
		return cl;
	}

	
	public Integer checkLoanAllotment(Integer customerId) throws Exception {
		
		CustomerEntity ce = entityManager.find(CustomerEntity.class, customerId);
		if(ce == null)
			return -1;
		else{
			if(ce.getLoan() == null)
				return 0;
			else
				return ce.getLoan().getLoanId();
		}
		
	}
	
	public Integer sanctionLoan(Customer customer) throws Exception {
		CustomerEntity e=entityManager.find(CustomerEntity.class,customer.getCustomerId());
		if(e!=null)
		{
			if(e.getLoan()==null){
				LoanEntity le=new LoanEntity();
				le.setLoanId(customer.getLoan().getLoanId());
				le.setLoanAmount(customer.getLoan().getLoanAmount());
				le.setLoanType(customer.getLoan().getLoanType());
				if(customer.getLoan().getLoanType().equals("HomeLoan"))
				{
					le.setInterestRate(13.0);
					le.setTerm(15);
				}
				else
				{
					le.setInterestRate(9.0);
					le.setTerm(5);
				}
				e.setLoan(le);
				entityManager.persist(le);
			}
		}
		return e.getLoan().getLoanId();
	}
}
