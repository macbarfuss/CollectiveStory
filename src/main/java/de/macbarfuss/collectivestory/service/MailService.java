package de.macbarfuss.collectivestory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import de.macbarfuss.collectivestory.model.dto.UserDto;

@Service
public final class MailService {

    @Autowired
    private MailSender mailSender;

    public MailService() {
    }

    public void sendPing() {
        final SimpleMailMessage message = newMessage();
        message.setSubject("Ping");
        message.setText("This is just a small message to tell you that sending an email was successful.");
        message.setTo("mail@macbarfuss.de");
        mailSender.send(message);
    }

    public void sendNewPassword(final UserDto user, final String password) {
        final SimpleMailMessage message = newMessage();
        message.setSubject("New Password for user " + user.getUsername());
        message.setText("The new password is " + password);
        message.setTo(user.getEmail());
        mailSender.send(message);
    }

    public boolean notifyNewUser(final UserDto user, final String password) {
        final SimpleMailMessage message = newMessage();
        message.setSubject("New user created for CollectiveStory");
        message.setText("A new user was created for the CollectiveStory-Platform.\n\n"
                + "Following data is recorded and can be used to log in:\n" + "username: " + user.getUsername() + "\n"
                + "password: " + password);
        message.setTo(user.getEmail());
        try {
            mailSender.send(message);
            return true;
        } catch (MailSendException e) {
            return false;
        }
    }

    private SimpleMailMessage newMessage() {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("collectivestory@macbarfuss.de");
        return message;
    }
}
