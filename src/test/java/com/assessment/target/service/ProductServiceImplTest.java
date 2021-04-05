package com.assessment.target.service;

import com.assessment.target.config.AppConfigService;
import com.assessment.target.dto.DomainResponse;
import com.assessment.target.dto.Item;
import com.assessment.target.dto.Product;
import com.assessment.target.dto.ProductDescription;
import com.assessment.target.exception.ProductDetailsNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl serviceUnderTest;
    @Mock
    AppConfigService appConfigService;
    @Mock
    RestTemplate restTemplate;

    private Method reflectionMethod;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        when(appConfigService.getRedskyUrl()).thenReturn("https://redsky.target.com/v3/pdp/tcin/");
    }

    @Test
    public void getProduct_successFlow() {
        DomainResponse domainResponse = DomainResponse.builder().
                product(Product.builder()
                        .item(Item.builder().tcin("123")
                                .product_description(ProductDescription.builder().title("The Big Lebowski (Blu-ray) (Widescreen)").build())
                                .build()).build()).build();

        ResponseEntity<DomainResponse> responseEntity = new ResponseEntity<DomainResponse>(domainResponse, HttpStatus.OK);
        when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(), ArgumentMatchers.<Class<DomainResponse>>any())).thenReturn(responseEntity);
        DomainResponse response = serviceUnderTest.getListOfProducts(123);
        assertNotNull(response);
    }

    @Test
    public void throwException_whenProductNotFound() {
        when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(), ArgumentMatchers.<Class<DomainResponse>>any())).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
        assertThrows(ProductDetailsNotFoundException.class, () -> {
            serviceUnderTest.getListOfProducts(123);
        });
    }

    @Test
    void getUrl_withId() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        reflectionMethod = serviceUnderTest.getClass().getDeclaredMethod("getUrl", Integer.class);
        reflectionMethod.setAccessible(true);
        String url = (String) reflectionMethod.invoke(serviceUnderTest, 123);
        assertNotNull(url);
    }
}
