package com.elite.customer_app.aspect;

import org.springframework.core.annotation.Order;

//@Aspect
@Order(3)
public class ApiAnalyticsAspect {

    //    @Before("com.elite.customer_app.aspect.AopUtil.repoAdviceExceptGetOneAndGetByIdPointcut()")
    public void beforeRepoAdviceExceptGetOneAndGetById(){
        System.out.println("---------> Executing @Before Analytic Aspect on method <---------");
    }

}
