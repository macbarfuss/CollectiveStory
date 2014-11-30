package de.macbarfuss.collectivestory.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import de.macbarfuss.collectivestory.interceptors.AddControllerNameToModelInterceptor;
import de.macbarfuss.collectivestory.interceptors.AddCurrentUserNameToModelInterceptor;

@Configuration
public class LayoutConfiguration extends WebMvcConfigurerAdapter {

    public LayoutConfiguration() {
        super();
    }

    @Override
    public void addInterceptors(final InterceptorRegistry reg) {
        reg.addInterceptor(new AddControllerNameToModelInterceptor());
        reg.addInterceptor(new AddCurrentUserNameToModelInterceptor());
    }
}
