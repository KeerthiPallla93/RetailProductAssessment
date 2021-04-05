package com.assessment.target.manager;


import com.assessment.target.dto.FinalProduct;
import com.assessment.target.exception.ProductServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;

@Component
@ConditionalOnProperty(prefix = "feature.product", name = "details", havingValue = "stub")
@Log4j2
@RequiredArgsConstructor
public class ProductManagerStub implements ProductManager{

    private static final String FILE_PATH = "productDetails.json";
    public static final String RESPONSES = "responses";
    private final ObjectMapper objectMapper;

    @Override
    public FinalProduct getListOfProducts(Integer id) {
        try {
            return objectMapper.readValue(
                    new ClassPathResource(Paths.get(RESPONSES, FILE_PATH).toString())
                            .getInputStream(),
                    FinalProduct.class);
        } catch (IOException e) {
            throw new ProductServiceException(e.getMessage());
        }
    }

    @Override
    public void updateProductDetails(FinalProduct request) {
        try {
            objectMapper.readValue(
                    new ClassPathResource(Paths.get(RESPONSES, FILE_PATH).toString())
                            .getInputStream(),
                    FinalProduct.class);
        } catch (IOException e) {
            throw new ProductServiceException(e.getMessage());
        }
    }
}
