package de.macbarfuss.collectivestory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import de.macbarfuss.collectivestory.model.AbstractConfiguration;
import de.macbarfuss.collectivestory.model.EmailConfiguration;
import de.macbarfuss.collectivestory.service.ConfigurationService;

@Configuration
public class MailConfiguration {

    @Autowired
    private ConfigurationService configurationService;

    public MailConfiguration() {
    }

    @Bean(name = "mailSender")
    public MailSender getMailSender() {
        final AbstractConfiguration config = configurationService.get(EmailConfiguration.class);

        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(config.getString(EmailConfiguration.HOST));
        mailSender.setPassword(config.getString(EmailConfiguration.PASSWORD));
        mailSender.setPort(config.getInteger(EmailConfiguration.PORT));
        mailSender.setUsername(config.getString(EmailConfiguration.USERNAME));

        return mailSender;
    }

}
