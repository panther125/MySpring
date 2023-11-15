package com.panther.springframework.context.event;

import com.panther.springframework.context.ApplicationEvent;

/**
 * @author Gin 琴酒
 * @data 2023/10/27 16:01
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}