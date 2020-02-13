package com.infy;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.infy.dao.LoanDAO;
import com.infy.model.Customer;
import com.infy.model.Loan;
import com.infy.service.LoanService;
import com.infy.service.LoanServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankLoanToTraineeApplicationTests {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Mock
	private LoanDAO loanDAO;

	@InjectMocks
	private LoanService loanService = new LoanServiceImpl();

	// DO NOT CHANGE METHOD SIGNATURE AND DELETE/COMMENT METHOD
	@Test
	public void sanctionLoanCustomerNotAvailableTest() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("Service.CUSTOMER_UNAVAILABLE");
		Customer customer= new Customer();
		customer.setCustomerId(2008);
		customer.setCustomerName("Harry");
		customer.setMobileNo(9874561230L);
		Loan loan=new Loan();
		loan.setInterestRate(10.0);
		loan.setLoanAmount(200000.0);
		loan.setLoanId(1007);
		loan.setLoanType("CarLoan");
		loan.setTerm(10);
		customer.setLoan(loan);
		Mockito.when(loanDAO.checkLoanAllotment(customer.getCustomerId())).thenReturn(-1);
		loanService.sanctionLoan(customer);

	}

	// DO NOT CHANGE METHOD SIGNATURE AND DELETE/COMMENT METHOD
	@Test
	public void sanctionLoanLoanAlreadyTakenTest() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("Service.LOAN_ALREADY_TAKEN");
		Customer customer= new Customer();
		customer.setCustomerId(2008);
		customer.setCustomerName("Harry");
		customer.setMobileNo(9874561230L);
		Loan loan=new Loan();
		loan.setInterestRate(10.0);
		loan.setLoanAmount(200000.0);
		loan.setLoanId(1007);
		loan.setLoanType("CarLoan");
		loan.setTerm(10);
		customer.setLoan(loan);
		Mockito.when(loanDAO.checkLoanAllotment(customer.getCustomerId())).thenReturn(1007);
		loanService.sanctionLoan(customer);

	}
}
