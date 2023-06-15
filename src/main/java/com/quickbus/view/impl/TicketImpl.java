package com.quickbus.view.impl;

import com.quickbus.model.Ticket;
import com.quickbus.model.Travel;
import com.quickbus.repository.TicketRepo;
import com.quickbus.repository.TravelRepo;
import com.quickbus.response.ResponseMap;
import com.quickbus.view.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class TicketImpl implements TicketService {
    @Autowired
    public TicketRepo ticketRepo;

    @Autowired
    public TravelRepo travelRepo;

    @Override
    public ResponseMap save(Ticket ticket) {
        try {
            LocalDate currentDate = LocalDate.now();
            //            check avail travel
            Optional<Travel> travelObj = travelRepo.checkAvailTravel(ticket.getTravel().getId(), currentDate);
            if (!travelObj.isPresent()) {
                return new ResponseMap().error(
                        HttpStatus.NOT_FOUND,
                        "Travel not found"
                );
            }

            //        check travel available seat
            if (travelObj.get().getAvailableSeat() < ticket.getPassenger()) {
                return new ResponseMap().error(
                        HttpStatus.BAD_REQUEST,
                        "Travel seats not available"
                );
            }

//            set travel to ticket obj
            ticket.setTravel(travelObj.get());
//            set price
            ticket.setPrice(ticket.getPassenger()*travelObj.get().getPrice());
            Ticket createdTicket = ticketRepo.save(ticket);

            return new ResponseMap().success(
                    createdTicket,
                    "Success create ticket"
            );

        }catch (Exception e){
            return new ResponseMap().error(
              HttpStatus.INTERNAL_SERVER_ERROR,
              "Failed to create ticket"
            );
        }
    }

    @Override
    public ResponseMap updateStatus(UUID ticketId,String status){
        try{
//            check ticket id
            Optional<Ticket> ticketObj = ticketRepo.findById(ticketId);
            if(!ticketObj.isPresent()){
                return new ResponseMap().error(
                  HttpStatus.NOT_FOUND,
                  "Ticket not found"
                );
            }

            Ticket updatedTicket = ticketObj.get();
            updatedTicket.setStatus(status);
            ticketRepo.save(updatedTicket);
            return new ResponseMap().success(updatedTicket,
                    "Success update ticket status");
        }catch (Exception e){
        return new ResponseMap().error(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Failed to update ticket status"
        );
        }
    }

    @Override
    public ResponseMap delete(UUID ticketId){
        try {
//            check ticket id
            Optional<Ticket> ticketObj = ticketRepo.findById(ticketId);
            if(!ticketObj.isPresent()){
                return new ResponseMap().error(
                        HttpStatus.NOT_FOUND,
                        "Ticket not found"
                );
            }

            if (!ticketObj.get().getStatus().equals("pending")){
                throw new Exception();
            }

            ticketRepo.deleteById(ticketId);
            return new ResponseMap().success(
                    ticketObj.get(),
                    "Success delete ticket"
            );
        }catch (Exception e){
            return new ResponseMap().error(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to delete ticket"
            );
        }
    }

    @Override
    public ResponseMap getTicketDetail(UUID ticketId){
        try {
//            check ticket id
            Optional<Ticket> ticketObj = ticketRepo.findById(ticketId);
            if(!ticketObj.isPresent()){
                return new ResponseMap().error(
                        HttpStatus.NOT_FOUND,
                        "Ticket not found"
                );
            }

            return new ResponseMap().success(
                    ticketObj.get(),
                    "Success get ticket detail"
            );
        }catch (Exception e){
            return new ResponseMap().error(
              HttpStatus.INTERNAL_SERVER_ERROR,
              "Failed to get travel detail"
            );
        }
    }
}
