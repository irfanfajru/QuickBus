package com.quickbus.repository;

import com.quickbus.model.UserDetail;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepo extends PagingAndSortingRepository<UserDetail,Long> {
}
