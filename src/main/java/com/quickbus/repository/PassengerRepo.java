package com.quickbus.repository;

import com.quickbus.model.Passenger;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface PassengerRepo extends PagingAndSortingRepository<Passenger, UUID> {

}
