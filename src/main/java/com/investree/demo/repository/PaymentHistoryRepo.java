package com.investree.demo.repository;

import com.investree.demo.model.PaymentHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PaymentHistoryRepo extends PagingAndSortingRepository<PaymentHistory, Long> {
    @Query("select c from PaymentHistory c")
    public List<PaymentHistory> getList();

    @Query("select c from PaymentHistory c where c.id = :id")
    public PaymentHistory getById(@Param("id") Long id);
}
