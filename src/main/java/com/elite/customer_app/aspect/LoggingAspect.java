package com.elite.customer_app.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.springframework.core.annotation.Order;

//@Aspect
@Order(2)
public class LoggingAspect {

    // @Before("execution( * com.elite.customer_app.controller.*.saveCustomer(..)")
    public void beforeAddCustomerAdvice(Joinpoint joinpoint){

        /* MethodSignature methodSignature = (MethodSignature) joinpoint.getMethodSignature();
        System.out.println("---------> Method signature: " + methodSignature); */

        /* Object[] args =joinpoint.getArgs();
        for(Object arg : args) {
            System.out.println("---------> Argument: " + arg);

            if(arg instance of Customer){
                Customer customer = (Customer) arg;
                System.out.println( customer.getFirstName());
                System.out.println( customer.getLastName());
                System.out.println( customer.getEmail());
            }
        } */

        System.out.println("---------> Executing @Before advice on saveCustomer() <--------");
    }

    //    @Before("com.elite.customer_app.aspect.AopUtil.repoPointCut()")
    public void beforeRepoAdvice(){
        System.out.println("---------> Executing @Before advice on method <---------");
    }

    //    @Before("com.elite.customer_app.aspect.AopUtil.repoAdviceExceptGetOneAndGetByIdPointcut()")
    public void beforeRepoAdviceExceptGetOneAndGetById(){
        System.out.println("---------> Executing @Before Logging Aspect on method <---------");
    }

}