package com.quickbus.repository;

import com.quickbus.model.Ticket;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface TicketRepo extends PagingAndSortingRepository<Ticket, UUID> {
}
