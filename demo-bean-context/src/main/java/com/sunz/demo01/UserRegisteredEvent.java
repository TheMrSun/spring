package com.sunz.demo01;

import org.springframework.context.ApplicationEvent;

/**
 * @author Slience
 * @version 1.0
 */
public class UserRegisteredEvent extends ApplicationEvent {
    public UserRegisteredEvent(Object source){
        super(source);
    }
}
