package org.jmri.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by shawn on 14/06/16.
 */
@SpringBootApplication
public class WebApplication {

    public static final void main(final String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
