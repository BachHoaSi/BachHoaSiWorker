package com.swd391.bachhoasi_worker.service;

import java.math.BigDecimal;

import com.swd391.bachhoasi_worker.model.dto.request.SearchRequestParamsDto;
import com.swd391.bachhoasi_worker.model.dto.response.PaginationResponse;
import com.swd391.bachhoasi_worker.model.dto.response.ShipperResponseDto;

public interface ShipperService {
    PaginationResponse<ShipperResponseDto> getAllShipper(SearchRequestParamsDto search);
    ShipperResponseDto getShipperDetail(BigDecimal id);
}
