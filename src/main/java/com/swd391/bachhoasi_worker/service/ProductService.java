package com.swd391.bachhoasi_worker.service;

import org.springframework.stereotype.Service;

import com.swd391.bachhoasi_worker.model.dto.request.ProductRequest;
import com.swd391.bachhoasi_worker.model.dto.request.SearchRequestParamsDto;
import com.swd391.bachhoasi_worker.model.dto.response.PaginationResponse;
import com.swd391.bachhoasi_worker.model.dto.response.ProductResponse;


@Service
public interface ProductService {

    PaginationResponse<ProductResponse> getProducts(SearchRequestParamsDto request);

    ProductResponse addNewProduct(ProductRequest request);

    void deleteProduct(String code);

    ProductResponse updateProduct(ProductRequest request, String code);
}
