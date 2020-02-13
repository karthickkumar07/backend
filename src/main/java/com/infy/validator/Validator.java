package com.infy.validator;


import com.infy.model.Loan;

public class Validator {

	//DO NOT CHANGE METHOD SIGNATURE OR DELETE/COMMENT METHOD
	public static void validate(Loan loan) throws Exception{
		// Your code goes here
		if(! validateLoanType(loan.getLoanType())){
			throw new Exception("Validator.INVALID_LOANTYPE");
		}


	}

	//DO NOT CHANGE METHOD SIGNATURE OR DELETE/COMMENT METHOD
	public static Boolean validateLoanType(String loanType) {
		// Your code goes here
		
		if(loanType.equals("HomeLoan") || loanType.equals("CarLoan")){
			return true;
		}
		return false;
	}

}
