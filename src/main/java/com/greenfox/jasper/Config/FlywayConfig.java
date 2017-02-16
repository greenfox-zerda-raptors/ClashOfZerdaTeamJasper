package com.greenfox.jasper.Config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URISyntaxException;

@Configuration
public class FlywayConfig {

    @Bean
    Flyway flyway() throws URISyntaxException { //Exception = "?"
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setSchemas("clash4");
        flyway.setLocations("filesystem:src/main/java/com/greenfox/jasper/db/migration");
//        flyway.repair();
        return flyway;
    }
}
