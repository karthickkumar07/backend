package com.infy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.model.Customer;
import com.infy.service.LoanService;

//DONT MODIFY NAME OF CLASS
//DONT ADD/MODIFY/DELETE/COMMENT ANY METHOD
//DONT DELETE/MODIFY INSTANCE VARIABLE(IF PRESENT)
//DONT MODIFY ANNOTATIONS(IF PRESENT)
@RestController
@RequestMapping(value="/loans")
public class LoanAPI {

	@Autowired
	private LoanService loanService;

	@Autowired
	public Environment environment;
	
	@PostMapping(value="/loan")
	public ResponseEntity<String> sanctionLoan(@RequestBody Customer customer)
			throws Exception {
		try{
		ResponseEntity<String> response=new ResponseEntity<String>
		(environment.getProperty("API.SANCTION_SUCCESS")+loanService.sanctionLoan(customer), HttpStatus.CREATED);
		
		return response;
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
		}
	}

	@GetMapping(value="/{loanType}")
	public ResponseEntity<List<Customer>> getReportByLoanType(@PathVariable String loanType) throws Exception {
		try{
			ResponseEntity<List<Customer>> response =new ResponseEntity<List<Customer>>
			(loanService.getReportByLoanType(loanType),HttpStatus.OK);
			return response;
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					environment.getProperty("Service.NO_LOAN_FOUND"));
		}
	}

}
