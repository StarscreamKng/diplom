package org.itstep.service;

import lombok.extern.slf4j.Slf4j;
import org.itstep.domain.entity.Subscriber;
import org.itstep.domain.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class EmailService {
    TaskExecutor taskExecutor;

    SubscriberRepository subscriberRepository;

    private JavaMailSender mailSenderObj;

    @Autowired
    public EmailService(SubscriberRepository subscriberRepository, JavaMailSender mailSenderObj, TaskExecutor taskExecutor) {
        this.subscriberRepository = subscriberRepository;
        this.mailSenderObj = mailSenderObj;
        this.taskExecutor = taskExecutor;
    }

    private void send(String subject, String body, String recipient) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("diplomjava@gmail.com"); // Отправитель
        email.setSubject(subject);
        email.setText(body);
        email.setTo(recipient); // Получатель
        log.info("Sent email: " + email);
        mailSenderObj.send(email);
    }


    public void sendEmailToAllSubscribers(String subject, String message) {
        subscriberRepository
                .findAll()
                .stream()
                .filter(Subscriber::getEnabled)
                .peek(s -> log.info("Send mail to: " + s))
                .forEach(s -> taskExecutor.execute(() -> send(subject, message, s.getEmail())));
    }
}
