package com.panther.springframework.context.event;

import com.panther.springframework.context.ApplicationContext;
import com.panther.springframework.context.ApplicationEvent;

/**
 * @author Gin 琴酒
 * @data 2023/10/27 16:00
 */
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
