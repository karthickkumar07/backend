package com.infy.utility;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//DONT MODIFY NAME OF CLASS
//DONT ADD/MODIFY/DELETE/COMMENT ANY METHOD
//DONT DELETE/MODIFY INSTANCE VARIABLE(IF PRESENT)
//DONT MODIFY ANNOTATIONS(IF PRESENT)
@Component
@Aspect
public class LoggingAspect {

	private Logger logger = LogManager.getLogger(this.getClass());

	@AfterThrowing(pointcut="execution(* com.infy.dao.*Impl.*(..))",throwing="exception")
	public void logDaoException(Exception exception) throws Exception {
		logger.log(Level.ERROR,exception.getMessage(),exception);
	}

	@AfterThrowing(pointcut="execution(* com.infy.service.*Impl.*(..))",throwing="exception")
	public void logServiceException(Exception exception) throws Exception {
		logger.log(Level.ERROR,exception.getMessage(),exception);
	}

}
