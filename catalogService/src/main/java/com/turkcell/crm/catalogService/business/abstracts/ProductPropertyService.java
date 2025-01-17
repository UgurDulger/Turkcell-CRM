package com.turkcell.crm.catalogService.business.abstracts;

import com.turkcell.crm.catalogService.business.dtos.request.productProperties.CreateProductPropertyRequest;
import com.turkcell.crm.catalogService.business.dtos.request.productProperties.UpdateProductPropertyRequest;
import com.turkcell.crm.catalogService.business.dtos.response.productProperties.CreatedProductPropertyResponse;
import com.turkcell.crm.catalogService.business.dtos.response.productProperties.GetAllProductPropertyResponse;
import com.turkcell.crm.catalogService.business.dtos.response.productProperties.GetByIdProductPropertyResponse;
import com.turkcell.crm.catalogService.business.dtos.response.productProperties.UpdatedProductProductResponse;

import java.util.List;

public interface ProductPropertyService {
    CreatedProductPropertyResponse add(CreateProductPropertyRequest createProductPropertyRequest);

    List<GetAllProductPropertyResponse> getAll();

    UpdatedProductProductResponse update(UpdateProductPropertyRequest updateProductPropertyRequest);

    GetByIdProductPropertyResponse getById(int id);

    void delete(int id);

}
