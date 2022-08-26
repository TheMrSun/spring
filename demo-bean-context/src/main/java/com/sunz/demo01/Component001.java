package com.sunz.demo01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author Slience
 * @version 1.0
 */
@Component
public class Component001 {
    private static final Logger log = LoggerFactory.getLogger(Component001.class);

    public void register(){
        log.debug("{}","用户注册了");
    }
}
