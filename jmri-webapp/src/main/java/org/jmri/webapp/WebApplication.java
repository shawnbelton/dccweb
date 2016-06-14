package org.jmri.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by shawn on 14/06/16.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class WebApplication {

    public static final void main(final String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
