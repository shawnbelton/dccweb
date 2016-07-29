package uk.co.redkiteweb.dccweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;

/**
 * Created by shawn on 15/06/16.
 */
@SpringBootApplication
@EnableScheduling
public class DccWebApplication {

    public static final void main(final String[] args) {
        SpringApplication.run(DccWebApplication.class, args);
    }

//    @Bean
//    public DataSource dataSource() {
//        return (new EmbeddedDatabaseBuilder()).build();
//    }
}
