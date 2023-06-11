package com.quickbus.repository;

import com.quickbus.model.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface LogRepo extends PagingAndSortingRepository<Log,Long> {

    @Query("select c from Log c where c.user_id = :user_id")
    public Page<Log> findByUserId(@Param("user_id") Long userId, Pageable pageable);

    public Page<Log> findAll(Pageable pageable);
}
