package com.elite.customer_app.aspect;

//@Aspect
public class AopUtil {

    //    @Pointcut("execution( * com.elite.customer_app.repository.*.*(..))")
    private void repoPointcut(){}

    //    @Pointcut("execution( * com.elite.customer_app.repository.*.getOne(..))")
    private void getOnePointcut(){}

    //    @Pointcut("execution( * com.elite.customer_app.repository.*.getById(..))")
    private void getByIdPointcut(){}

    //    @Pointcut("repoPointcut() && !(getOnePointcut() || getByIdPointcut())")
    private void repoAdviceExceptGetOneAndGetByIdPointcut(){
    }

}
