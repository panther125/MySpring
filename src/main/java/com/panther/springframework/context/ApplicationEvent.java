package com.panther.springframework.context;

import java.util.EventObject;

/**
 * @author Gin 琴酒
 * @data 2023/10/27 15:58
 */
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
