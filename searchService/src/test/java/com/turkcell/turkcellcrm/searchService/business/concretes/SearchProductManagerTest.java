package com.turkcell.turkcellcrm.searchService.business.concretes;

import com.turkcell.turkcellcrm.common.events.product.ProductCreatedEvent;
import com.turkcell.turkcellcrm.common.events.product.ProductUpdatedEvent;
import com.turkcell.turkcellcrm.searchService.business.abstracts.SearchService;
import com.turkcell.turkcellcrm.searchService.business.dto.dynamics.DynamicFilter;
import com.turkcell.turkcellcrm.searchService.business.dto.dynamics.DynamicQuery;
import com.turkcell.turkcellcrm.searchService.business.dto.dynamics.DynamicSort;
import com.turkcell.turkcellcrm.searchService.business.dto.response.GetAllProductResponse;
import com.turkcell.turkcellcrm.searchService.business.messages.ProductFilterBusinessRulesMessages;
import com.turkcell.turkcellcrm.searchService.business.rules.ProductFilterBusinessRules;
import com.turkcell.turkcellcrm.searchService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.turkcellcrm.searchService.core.utilities.mapping.ModelMapperService;
import com.turkcell.turkcellcrm.searchService.dataAccess.SearchProductRepository;
import com.turkcell.turkcellcrm.searchService.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchProductManagerTest {

    @Mock
    private SearchProductRepository searchProductRepository;

    @Mock
    private ProductFilterBusinessRules productFilterBusinessRules;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ModelMapperService modelMapperService;

    @Mock
    private SearchService searchService;

    @InjectMocks
    private SearchProductManager searchProductManager;


    @Test
    void testAdd() {
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        productCreatedEvent.setProductId(1);
        productCreatedEvent.setCatalogId(1);
        productCreatedEvent.setName("Iphone");

        Product product = new Product();
        product.setProductId(1);
        product.setCatalogId(1);
        product.setName("Iphone");

        when(modelMapperService.forRequest()).thenReturn(modelMapper);
        when(modelMapper.map(productCreatedEvent,Product.class)).thenReturn(product);

        searchProductManager.add(productCreatedEvent);

        verify(modelMapperService.forRequest()).map(productCreatedEvent,Product.class);
        verify(searchProductRepository).save(product);
        assertDoesNotThrow(()->searchProductManager.add(productCreatedEvent));

    }

    @Test
    void testGetAll() {
        List<DynamicFilter> filters = Collections.emptyList();
        List<DynamicSort> sorts = Collections.emptyList();
        DynamicQuery dynamicQuery = new DynamicQuery(filters, sorts);

        Product product = new Product();
        List<Product> productList = Arrays.asList(product);
        GetAllProductResponse response = new GetAllProductResponse();

        when(modelMapperService.forResponse()).thenReturn(modelMapper);
        when(searchService.dynamicSearch(dynamicQuery, Product.class)).thenReturn(productList);
        when(modelMapperService.forResponse().map(product, GetAllProductResponse.class)).thenReturn(response);

        List<GetAllProductResponse> responses = searchProductManager.getAll(dynamicQuery);

        verify(searchService).dynamicSearch(dynamicQuery, Product.class);
        verify(modelMapperService.forResponse()).map(product, GetAllProductResponse.class);
    }

    @Test
    void testUpdate() {

        ProductUpdatedEvent productUpdatedEvent = new ProductUpdatedEvent();
        Product product = new Product();

        when(modelMapperService.forRequest()).thenReturn(modelMapper);
        when(modelMapperService.forRequest().map(productUpdatedEvent, Product.class)).thenReturn(product);

        searchProductManager.update(productUpdatedEvent);

        verify(modelMapperService.forRequest()).map(productUpdatedEvent, Product.class);
        verify(searchProductRepository).save(product);
        assertDoesNotThrow(()->searchProductManager.update(productUpdatedEvent));
    }

    @Test
    void testDelete_Successful() {
        int productId = 150;

        Product product = new Product();
        product.setProductId(productId);
        product.setCatalogId(1);
        product.setName("Samsung");
        product.setDeletedDate(null); // Initially not deleted

        // Mock the business rule to return the product
        when(productFilterBusinessRules.IsProductAlreadyDeleted(productId)).thenReturn(product);

        // Perform the delete operation
        searchProductManager.delete(productId);

        // Verify that the methods were called
        verify(productFilterBusinessRules).IsProductAlreadyDeleted(productId);
        verify(searchProductRepository).save(product);

        // Check if the deleted date is set
        assertNotNull(product.getDeletedDate());
    }
}

