package com.quickbus.controller;

import com.quickbus.model.Ticket;
import com.quickbus.model.oauth.User;
import com.quickbus.repository.TicketRepo;
import com.quickbus.repository.oauth.UserRepo;
import com.quickbus.response.ResponseMap;
import com.quickbus.view.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/v1/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @Autowired
    TicketRepo ticketRepo;

    @Autowired
    UserRepo userRepo;

    @PostMapping("")
    public ResponseEntity<ResponseMap> save(
            @Valid
            @RequestBody Ticket ticket,
            HttpServletRequest request
            ){
        User user = userRepo.findOneByUsername(request.getUserPrincipal().getName());
        ticket.setUser(user);
        ResponseMap response = ticketService.save(ticket);
        return new ResponseEntity<ResponseMap>(response,response.getCode());
    }

    @PutMapping("/{ticketId}")
    public ResponseEntity<ResponseMap> updateStatus(
            @Valid
            @PathVariable UUID ticketId,
            @RequestParam String status
            ){
        ResponseMap response = ticketService.updateStatus(ticketId,status);
        return new ResponseEntity<ResponseMap>(response,response.getCode());
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<ResponseMap> delete(
            @PathVariable UUID ticketId
    ){
        ResponseMap response = ticketService.delete(ticketId);
        return new ResponseEntity<ResponseMap>(response,response.getCode());
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<ResponseMap> getTicketDetail(
            @PathVariable UUID ticketId
    ){
        ResponseMap response = ticketService.getTicketDetail(ticketId);
        return new ResponseEntity<ResponseMap>(response,response.getCode());
    }

    @GetMapping("")
    public ResponseEntity<Page<Ticket>> getTickets(
            @RequestParam() Integer page,
            @RequestParam() Integer size
    ){
        Pageable showData = PageRequest.of(page,size);
        Page<Ticket> list = ticketRepo.findAll(showData);
        return new ResponseEntity<Page<Ticket>>(list,new HttpHeaders(), HttpStatus.OK);
    }
}
