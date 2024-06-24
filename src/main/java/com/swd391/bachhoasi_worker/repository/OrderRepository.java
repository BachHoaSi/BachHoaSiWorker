package com.swd391.bachhoasi_worker.repository;
import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.swd391.bachhoasi_worker.model.entity.Order;
@Repository
public interface OrderRepository extends BaseBachHoaSiRepository<Order, BigDecimal> {
    
}
