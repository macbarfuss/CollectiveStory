package de.macbarfuss.collectivestory.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Beans for general purpose.
 * 
 * @author geoema
 * 
 */
@Configuration
public class BasicConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(BasicConfiguration.class);

    public BasicConfiguration() {
    }

    @Bean(name = "messageSource")
    public MessageSource getMessageSource() {
        LOG.debug("Initializing messageSource");
        final ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
        bean.setBasename("Messages");
        return bean;
    }
}
