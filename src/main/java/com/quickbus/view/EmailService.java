package com.quickbus.view;

import com.quickbus.model.EmailDetail;
import com.quickbus.model.Ticket;

import javax.mail.MessagingException;

public interface EmailService {
    public void sendCreatedTicketEmail(Ticket ticket) throws MessagingException;
}
