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
    @Query("select c from Bus c")
    public Page<Bus> findAll(Pageable pageable);

    @Query("select c from Bus c where id=:id and deleted_at is null")
    public Optional<Bus> findById(UUID id);

    public Page<Bus> findByNameLike(String name,Pageable pageable);

    public Page<Bus> findByTypeLike(String type,Pageable pageable);
}
