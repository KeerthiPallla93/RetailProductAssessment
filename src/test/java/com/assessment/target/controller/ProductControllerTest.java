package com.assessment.target.controller;

import com.assessment.target.dto.FinalProduct;
import com.assessment.target.manager.ProductManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static com.assessment.target.util.TestUtils.getCurrentPrice;
import static com.assessment.target.util.TestUtils.getFinalProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @InjectMocks
    ProductController productController;
    @Mock
    ProductManager productManager;

    @Captor
    ArgumentCaptor<FinalProduct> finalProductCaptor;

    @Test
    void productDetails_validRequest() {
        FinalProduct finalProduct = getFinalProduct(132424, getCurrentPrice());
        when(productManager.getListOfProducts(any())).thenReturn(finalProduct);

        ResponseEntity<FinalProduct> result = productController.getProductDetails(132424);
        assertEquals(200, result.getStatusCode().value());
    }


    @Test
    void updateProductDetails() {
        FinalProduct finalProduct = getFinalProduct(132424, getCurrentPrice());
        productController.updateProductDetails(finalProduct);
        verify(productManager, times(1)).updateProductDetails(any());
        verify(productManager).updateProductDetails(finalProductCaptor.capture());
        FinalProduct finalProductValue = finalProductCaptor.getValue();
        assertEquals(finalProduct, finalProductValue);
    }
}
