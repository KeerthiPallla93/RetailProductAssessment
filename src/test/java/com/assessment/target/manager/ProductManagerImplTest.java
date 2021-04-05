package com.assessment.target.manager;

import com.assessment.target.dto.FinalProduct;
import com.assessment.target.mapper.MapProductResponse;
import com.assessment.target.repository.PriceInfoRepository;
import com.assessment.target.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.assessment.target.util.TestUtils.getCurrentPrice;
import static com.assessment.target.util.TestUtils.getDomainResponse;
import static com.assessment.target.util.TestUtils.getFinalProduct;
import static com.assessment.target.util.TestUtils.getPriceInfo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductManagerImplTest {

    @InjectMocks
    ProductManagerImpl productManagerImpl;

    @Mock
    private ProductService productService;
    @Mock
    private MapProductResponse mapProductResponse;
    @Mock
    private PriceInfoRepository priceInfoRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getProductDetails_success() {
        when(productService.getListOfProducts(any())).thenReturn(getDomainResponse());
        when(priceInfoRepository.findById(any())).thenReturn(getPriceInfo());
        when(mapProductResponse.getProductDetails(any(), any())).thenReturn(getFinalProduct(123, getCurrentPrice()));

        FinalProduct product1 = productManagerImpl.getListOfProducts(123);
        assertNotNull(product1);
        assertNotNull(product1.getId());
    }

    @Test
    void getProductDetails_whenPriceNotFound() {
        when(productService.getListOfProducts(any())).thenReturn(getDomainResponse());
        when(priceInfoRepository.findById(any())).thenReturn(null);
        when(mapProductResponse.getProductDetails(any(), any())).thenReturn(getFinalProduct(123, null));

        FinalProduct product1 = productManagerImpl.getListOfProducts(123);
        assertNotNull(product1);
        assertNotNull(product1.getId());
        assertNull(product1.getCurrent_price());
    }
}
