package uk.co.redkiteweb.dccweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:/application.properties"})
public class DummyRelay {

    public static void main(final String[] args) {
        SpringApplication.run(DummyRelay.class, args);
    }

}
