package org.jmri.webapp.listeners;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by shawn on 14/06/16.
 */
public class StartUpListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(final ApplicationStartedEvent applicationStartedEvent) {

    }
}
