package com.elite.customer_app.aspect;

import org.springframework.core.annotation.Order;

//@Aspect
@Order(1)
public class CloudLogAsyncAspect {

    //    @Before("com.elite.customer_app.aspect.AopUtil.repoAdviceExceptGetOneAndGetByIdPointcut()")
    public void beforeRepoAdviceExceptGetOneAndGetById(){
        System.out.println("---------> Executing @Before Cloud Aspect on method <---------");
    }

}
