package com.elite.customer_app.aspect;

import com.elite.customer_app.model.Customer;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Aspect
@Order(2)
@Component
public class LoggingAspect {
    Logger logger = Logger.getLogger(getClass().getName());

    @Before("execution(* com.elite.customer_app.controller.*.saveCustomer(..))")
    public void beforeAddCustomerAdvice(JoinPoint joinpoint){
        String methodSignature = joinpoint.getSignature().toShortString();
        logger.info("---------> Method signature: " + methodSignature);

        Object[] args = joinpoint.getArgs();
        for(Object arg : args) {
            logger.info("---------> Argument: " + arg);

            if(arg instanceof Customer){
                Customer customer = (Customer) arg;
                logger.info(customer.getFirstName());
                logger.info(customer.getLastName());
                logger.info(customer.getEmail());
            }
        }

        logger.info("---------> Executing @Before advice on saveCustomer() <--------");
    }

    @Before("com.elite.customer_app.aspect.AopUtil.repoPointCut()")
    public void beforeRepoAdvice(){
        logger.info("---------> Executing @Before advice on method <---------");
    }

    @Before("com.elite.customer_app.aspect.AopUtil.repoAdviceExceptGetOneAndGetByIdPointcut()")
    public void beforeRepoAdviceExceptGetOneAndGetById(){
        logger.info("---------> Executing @Before Logging Aspect on method <---------");
    }

    @AfterReturning(
            pointcut = "execution(* com.elite.customer_app.repository.CustomerRepository.findAll(..))",
            returning = "customers"
    )
    public void afterFindAllCustomersAdvice(JoinPoint joinpoint, List<Customer> customers){
        String method = joinpoint.getSignature().toShortString();
        logger.info("----------> @AfterReturning on method: " + method);

        convertLastNameToUpper(customers);

        logger.info("----------> End of @AfterReturning on method: " + method);
    }

    private void convertLastNameToUpper(List<Customer> customers) {
        customers.forEach(customer -> customer.setLastName(customer.getLastName().toUpperCase()));
    }

    @AfterThrowing(
            pointcut = "execution(* com.elite.customer_app.repository.CustomerRepository.findAll(..))",
            throwing = "exception"
    )
    public void afterThrowingFindAllCustomersAdvice(JoinPoint joinpoint, Throwable exception){
        String method = joinpoint.getSignature().toShortString();
        logger.info("----------> @AfterThrowing on method: " + method);
        logger.info("----------> The exception is: " + exception);
    }

    @After("execution(* com.elite.customer_app.service.CustomerService.findAll(..))")
    public void afterFindAllCustomerAdvice(JoinPoint joinpoint){
        String method = joinpoint.getSignature().toShortString();
        logger.info("----------> @After (Finally) on method: " + method);
    }

    @Around("execution(* com.elite.customer_app.repository.CustomerRepository.delete(..))")
    public Object aroundDeleteCustomer(ProceedingJoinPoint proceedingJointPoint) throws Throwable {
        String method = proceedingJointPoint.getSignature().toShortString();
        logger.info("----------> @Around on method: " + method);

        long begin = System.currentTimeMillis();

        Object result = null;

        try {
            result = proceedingJointPoint.proceed();
        } catch (Exception e) {
            logger.warning(e.getMessage());
            result = "Major accident, but do not worry!";
        }

        long end = System.currentTimeMillis();
        long duration = end - begin;

        logger.info("----------> It takes : " + duration + " milliseconds");

        return result;
    }
}
