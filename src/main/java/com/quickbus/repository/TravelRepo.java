package com.quickbus.repository;

import com.quickbus.model.Travel;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface TravelRepo extends PagingAndSortingRepository<Travel, UUID> {

}
