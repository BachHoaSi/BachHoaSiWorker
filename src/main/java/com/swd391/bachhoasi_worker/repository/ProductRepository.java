package com.swd391.bachhoasi_worker.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.swd391.bachhoasi_worker.model.entity.Product;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ProductRepository extends BaseBachHoaSiRepository<Product,BigDecimal>{


    Page<Product> findByCategoryIdAndNameContainingIgnoreCase(BigDecimal categoryId, String search, Pageable pageable);
    Page<Product> findByNameContainingIgnoreCase(String search, Pageable pageable);
    Optional<Product> findByProductCode(String code);

}
