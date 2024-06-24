package com.swd391.bachhoasi_worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
@NoRepositoryBean
public interface BaseBachHoaSiRepository <T,K> extends JpaRepository<T,K>, QuerydslPredicateExecutor<T> {
}
