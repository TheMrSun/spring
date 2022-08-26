package com.sunz.demo01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Slience
 * @version 1.0
 */
@Component
public class Component02 {
    private static final Logger log = LoggerFactory.getLogger(Component02.class);

    @EventListener
    public void userRegisterListen(UserRegisteredEvent event){
        log.debug("{}",event);
        log.debug("{}","发送短信");
    }

}
