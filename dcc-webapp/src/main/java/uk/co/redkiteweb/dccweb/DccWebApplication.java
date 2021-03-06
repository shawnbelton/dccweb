package uk.co.redkiteweb.dccweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

/**
 * Created by shawn on 15/06/16.
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
@PropertySource({"classpath:/settings.properties","file:config/application.properties"})
public class DccWebApplication {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource.dccweb")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    public static void main(final String[] args) {
        SpringApplication.run(DccWebApplication.class, args);
    }

}
