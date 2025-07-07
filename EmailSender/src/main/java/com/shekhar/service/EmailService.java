package com.shekhar.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.shekhar.model.Email;
import com.shekhar.repository.EmailRepository;

import jakarta.transaction.Transactional; 

@Service
public class EmailService {

    private final JavaMailSender mailSender; 
    private final EmailRepository emailRepository; 
    
        @Autowired
    public EmailService(JavaMailSender mailSender, EmailRepository emailRepository) {
        this.mailSender = mailSender;
        this.emailRepository = emailRepository;
    }

    @Transactional 
    public boolean sendAndSaveEmail(String to, String subject, String body) {
        try {
        	
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("your_email@gmail.com"); 
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);

           
            Email email = new Email();
            email.setRecipient(to);
            email.setSubject(subject);
            email.setBody(body);
            email.setSentAt(LocalDateTime.now()); 
            emailRepository.save(email); 

            System.out.println("Email sent successfully to: " + to);
            return true;
        } catch (MailException e) {
            System.err.println("Error sending email to " + to + ": " + e.getMessage());
            
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while sending or saving email: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}