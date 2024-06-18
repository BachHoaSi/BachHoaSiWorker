package com.swd391.bachhoasi_worker.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.swd391.bachhoasi_worker.model.entity.Store;

import java.math.BigDecimal;

@Repository
public interface StoreRepository extends BaseBachHoaSiRepository<Store, BigDecimal> {

    Page<Store> findByNameContainingIgnoreCase(String search, Pageable pageable);
}
