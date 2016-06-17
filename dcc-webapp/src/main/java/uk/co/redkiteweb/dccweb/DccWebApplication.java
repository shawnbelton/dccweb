package uk.co.redkiteweb.dccweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by shawn on 15/06/16.
 */
@SpringBootApplication
@EnableScheduling
public class DccWebApplication {

    public static final void main(final String[] args) {
        SpringApplication.run(DccWebApplication.class, args);
    }
}
