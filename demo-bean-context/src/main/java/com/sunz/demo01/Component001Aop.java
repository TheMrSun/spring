package com.sunz.demo01;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Slience
 * @version 1.0
 */

@Aspect
@Component
public class Component001Aop {
    private static final Logger log = LoggerFactory.getLogger(Component001Aop.class);


    /**
     * 后置通知
     */
    @After("execution (* com.sunz.demo01.Component001.register(..))")
    public void after(){
        log.debug("{}","发送电子邮件");
    }

}
