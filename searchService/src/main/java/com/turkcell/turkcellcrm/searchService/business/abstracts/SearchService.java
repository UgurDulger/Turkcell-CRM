package com.turkcell.turkcellcrm.searchService.business.abstracts;

import com.turkcell.turkcellcrm.searchService.business.dto.request.GetAllCustomerRequest;
import com.turkcell.turkcellcrm.searchService.business.dto.response.GetAllCustomerResponse;
import com.turkcell.turkcellcrm.searchService.entities.Customer;

import java.util.List;

public interface SearchService {
    void add(Customer customer);
    List<GetAllCustomerResponse> getAll(GetAllCustomerRequest getAllCustomerRequest);
    void update(Customer customer);
    void deleteCustomer(int id);

}