package com.integration.mercans.parsers.config;

import com.integration.mercans.parsers.factory.ParserFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class represents a simple parser factory for creating Parser instances.
 * It uses a service locator interface to lookup Parser implementations based on a string identifier.
 *
 * @author Your Name
 * @version 1.0
 */
@Configuration
public class ParserConfig {

    /**
     * Creates a new instance of a Parser based on the given identifier.
     *
     * @return A new Parser instance, or null if no Parser implementation was found for the given identifier.
     * @throws IllegalArgumentException If the identifier is null or empty.
     */
    @Bean("parserFactory")
    public FactoryBean serviceLocatorFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(ParserFactory.class);
        return factoryBean;
    }
}
