package org.nrg.sandbox.configuration;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

/**
 * Sets up the database configuration for XNAT.
 */
@Configuration
public class PropertiesConfig implements EnvironmentAware {

    @Bean
    public PropertySourcesPlaceholderConfigurer properties() {
        Properties properties = new Properties();
        properties.put("datasource.driver", _environment.getProperty("datasource.driver"));
        properties.put("datasource.url", _environment.getProperty("datasource.url"));
        properties.put("datasource.username", _environment.getProperty("datasource.username"));
        properties.put("datasource.password", _environment.getProperty("datasource.password"));
        
        final MutablePropertySources sources = new MutablePropertySources();
        sources.addFirst(new PropertiesPropertySource("services", properties));
        final PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setPropertySources(sources);
        configurer.setIgnoreResourceNotFound(true);
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }

    @Override
    public void setEnvironment(final Environment environment) {
        _environment = environment;
    }

    private Environment _environment;
}
