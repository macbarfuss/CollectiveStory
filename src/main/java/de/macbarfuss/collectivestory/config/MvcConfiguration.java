package de.macbarfuss.collectivestory.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import de.macbarfuss.collectivestory.converter.DateFormatter;

/**
 * Configuration of Web MVC.
 * 
 * @author geoema
 * 
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "de.macbarfuss.collectivestory", excludeFilters = { @ComponentScan.Filter(
        type = FilterType.ANNOTATION, value = Configuration.class) })
@Import({ BasicConfiguration.class, MongodbConfiguration.class, ThymeleafConfig.class })
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(MvcConfiguration.class);

    @Autowired
    private MessageSource messageSource;

    public MvcConfiguration() {
    }

    @Bean(name = "mvcConversionService")
    public FormatterRegistry getMvcConversionService() {
        final FormatterRegistry bean = new FormattingConversionService();
        LOG.debug("Initializing mvcConversionService");
        if (messageSource != null) {
            LOG.debug("MessageSource is autowired.");
            bean.addFormatter(new DateFormatter(messageSource));
        } else {
            LOG.error("MessageSource was not autowired!");
        }
        return bean;
    }

    @Override
    public final void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("/resources/images/");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

}
