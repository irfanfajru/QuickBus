package com.quickbus.repository;

import com.quickbus.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface TicketRepo extends PagingAndSortingRepository<Ticket, UUID> {

    @Query("select c from Ticket c where c.id=:id and c.user.id=:userId")
    public Optional<Ticket> findByIdAndUserId(@Param("id")UUID id, @Param("userId") Long userId);

    @Query("select c from Ticket c where c.user.id=:userId")
    public Page<Ticket> findAllByUserId(@Param("userId") Long userId, Pageable pageable);
}
