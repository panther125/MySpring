package com.panther.springframework.context.event;

import com.panther.springframework.context.ApplicationEvent;
import com.panther.springframework.context.ApplicationListener;

/**
 * @author Gin 琴酒
 * @data 2023/10/27 16:02
 */
public interface ApplicationEventMulticaster {

    /**
     * Add a listener to be notified of all events.
     * @param listener the listener to add
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * Remove a listener from the notification list.
     * @param listener the listener to remove
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * Multicast the given application event to appropriate listeners.
     * @param event the event to multicast
     */
    void multicastEvent(ApplicationEvent event);
}
