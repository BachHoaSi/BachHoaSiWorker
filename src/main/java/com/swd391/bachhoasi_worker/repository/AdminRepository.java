package com.swd391.bachhoasi_worker.repository;

import org.springframework.stereotype.Repository;

import com.swd391.bachhoasi_worker.model.entity.Admin;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface AdminRepository extends BaseBachHoaSiRepository<Admin, BigDecimal> {

    Optional<Admin> findByUsername(String username);
}
