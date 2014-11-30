package de.macbarfuss.collectivestory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * Configuration of the Template Engine.
 * 
 * In this implementation the Thymeleaf Engine is used.
 * 
 * @author geoema
 * 
 */
@Configuration
public class ThymeleafConfig {

    public ThymeleafConfig() {
    }

    @Bean(name = "templateResolver")
    public TemplateResolver getTemplateResolver() {
        final TemplateResolver bean = new ServletContextTemplateResolver();
        bean.setPrefix("/WEB-INF/templates/");
        bean.setSuffix(".html");
        bean.setTemplateMode("HTML5");
        return bean;
    }

    @Bean(name = "templateEngine")
    public SpringTemplateEngine getTemplateEngine() {
        final SpringTemplateEngine bean = new SpringTemplateEngine();
        bean.setTemplateResolver(getTemplateResolver());
        bean.addDialect(new SpringSecurityDialect());
        return bean;
    }

    @Bean(name = "viewResolver")
    public ViewResolver getViewResolver() {
        final ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(getTemplateEngine());
        resolver.setOrder(1);
        return resolver;
    }

}
