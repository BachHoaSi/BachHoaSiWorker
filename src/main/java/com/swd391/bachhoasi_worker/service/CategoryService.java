package com.swd391.bachhoasi_worker.service;

import java.math.BigDecimal;

import com.swd391.bachhoasi_worker.model.dto.request.CategoryRequest;
import com.swd391.bachhoasi_worker.model.dto.request.SearchRequestParamsDto;
import com.swd391.bachhoasi_worker.model.dto.response.CategoryDetailResponse;
import com.swd391.bachhoasi_worker.model.dto.response.CategoryResponse;
import com.swd391.bachhoasi_worker.model.dto.response.PaginationResponse;

public interface CategoryService {
    PaginationResponse<CategoryResponse> getAllCategory();
    public PaginationResponse<CategoryResponse> getPaginationCategory(SearchRequestParamsDto search);
    public CategoryDetailResponse getCategoryDetail(BigDecimal id);

    public CategoryResponse insertCategory(CategoryRequest request);

    public CategoryResponse updateCategory(BigDecimal id, CategoryRequest request);
    public CategoryResponse removeCategory(BigDecimal id);
}
