package com.quickbus.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransaksiRepo extends PagingAndSortingRepository<Transaksi,Long> {
    @Query("select c from Transaksi c")
    public List<Transaksi> getList();

    @Query("select c from Transaksi c where c.id = :id")
    public Transaksi getById(@Param("id") Long id);

    public Page<Transaksi> findByStatusLike(String status, Pageable pageable);

    @Query("select c from Transaksi c")
    public Page<Transaksi> findAll(Pageable pageable);
}
