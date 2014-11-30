package de.macbarfuss.collectivestory.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserCreator implements ApplicationListener<ContextRefreshedEvent> {

    public DefaultUserCreator() {
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        // just stays here to show how to add a handler.
    }

}
