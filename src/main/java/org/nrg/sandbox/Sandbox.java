package org.nrg.sandbox;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.StandardEnvironment;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class Sandbox {
    public static void main(String[] args) {
        final Map<String, Object> properties = new HashMap<>();
        properties.put("datasource.driver", "org.postgresql.Driver");
        properties.put("datasource.url", "jdbc:postgresql://xnatdev/xnat");
        properties.put("datasource.username", "xnat");
        properties.put("datasource.password", "xnat");

        final ConfigurableEnvironment environment = new StandardEnvironment();
        environment.getPropertySources().addFirst(new MapPropertySource("properties", properties));

        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.setEnvironment(environment);
        context.scan("org.nrg.sandbox.configuration");
        context.refresh();

        final DataSource dataSource = context.getBean(DataSource.class);
        if (dataSource != null) {
            System.out.println("Found a data source");
        } else {
            System.out.println("Did not find a data source");
        }
    }
}

