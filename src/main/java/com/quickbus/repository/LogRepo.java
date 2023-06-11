package com.quickbus.repository;

import com.quickbus.model.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface LogRepo extends PagingAndSortingRepository<Log,Long> {
    public Page<Log> findAll(Pageable pageable);
}
