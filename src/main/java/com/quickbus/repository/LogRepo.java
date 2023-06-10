package com.quickbus.repository;

import com.quickbus.model.Log;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LogRepo extends PagingAndSortingRepository<Log,Long> {

}
