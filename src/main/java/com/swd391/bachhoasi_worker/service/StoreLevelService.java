package com.swd391.bachhoasi_worker.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.swd391.bachhoasi_worker.model.dto.request.StoreLevelRequest;
import com.swd391.bachhoasi_worker.model.dto.response.PaginationResponse;
import com.swd391.bachhoasi_worker.model.dto.response.StoreLevelResponse;

public interface StoreLevelService {
    PaginationResponse<StoreLevelResponse> getStoreLevelList(Pageable pageable, Map<String,String> parameters);
    StoreLevelResponse createNewStoreLevel(StoreLevelRequest storeLevelRequest);
    StoreLevelResponse removeStoreLevelById(BigDecimal id);
}
