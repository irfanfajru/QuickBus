package com.quickbus.repository;

import com.quickbus.model.Bus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface BusRepo extends PagingAndSortingRepository<Bus, UUID> {
    @Query("select c from Bus c where deleted_at is null")
    public Page<Bus> findAll(Pageable pageable);
    @Query("select c from Bus c where id=:id and deleted_at is null")
    public Optional<Bus> findById(UUID id);
    @Query("select c from Bus c where name like :name and deleted_at is null")
    public Page<Bus> findByNameLike(String name,Pageable pageable);
}
