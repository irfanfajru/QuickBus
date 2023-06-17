package com.quickbus.view;

import com.quickbus.model.Ticket;
import com.quickbus.model.oauth.User;
import com.quickbus.response.ResponseMap;

import java.util.UUID;

public interface TicketService {
    public ResponseMap save(Ticket ticket);
    public ResponseMap updateStatus(UUID ticketId,String status);
    public ResponseMap deleteUserTicket(UUID ticketId);
    public ResponseMap getUserTicketDetail(UUID ticketId);
    public ResponseMap getTicketDetail(UUID ticketId);
}
