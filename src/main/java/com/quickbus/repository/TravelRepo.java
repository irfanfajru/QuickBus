package com.quickbus.repository;

import com.quickbus.model.Travel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface TravelRepo extends PagingAndSortingRepository<Travel, UUID> {

    @Query("select c from Travel c where c.id=:id and c.deletedAt is null")
    public Optional<Travel> findById(@Param("id") UUID id);
    @Query("select c from Travel c where c.departure like :departure and c.arrival like :arrival and c.departureDate=:departureDate and c.deletedAt is null")
    public Page<Travel> findTravel(@Param("departure") String departure, @Param("arrival") String arrival, @Param("departureDate") LocalDate departureDate, Pageable pageable);

}
