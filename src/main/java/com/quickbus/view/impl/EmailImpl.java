package com.quickbus.view.impl;

import com.quickbus.model.Ticket;
import com.quickbus.view.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;
    private String sender = "quickbus@mail.com";

    @Override
    public void sendCreatedTicketEmail(Ticket ticket) throws MessagingException {
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            Context context = new Context();
            Map<String, Object> properties = new HashMap<>();
            properties.put("ticketId",ticket.getId());
            context.setVariables(properties);
            helper.setFrom(sender);
            helper.setTo(ticket.getUser().getUsername());
            helper.setSubject("Ticket Booking");
            String html = templateEngine.process("create-ticket-email-template.html", context);
            helper.setText(html, true);
            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
