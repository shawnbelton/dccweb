package uk.co.redkiteweb.dccweb.config;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;

@Configuration
public class EventBusConfig {

    @Bean
    public EventBus getEventBus(@Qualifier("executor") final Executor executor) {
        return new AsyncEventBus(executor);
    }

}
