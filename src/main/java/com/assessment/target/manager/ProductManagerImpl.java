package com.assessment.target.manager;

import com.assessment.target.data.PriceInfo;
import com.assessment.target.dto.DomainResponse;
import com.assessment.target.dto.FinalProduct;
import com.assessment.target.mapper.MapProductResponse;
import com.assessment.target.repository.PriceInfoRepository;
import com.assessment.target.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
@ConditionalOnProperty(prefix = "feature.product", name = "details", havingValue = "V1")
public class ProductManagerImpl implements ProductManager {

    private final ProductService productService;
    private final MapProductResponse mapProductResponse;
    private final PriceInfoRepository priceInfoRepository;

    @Override
    public FinalProduct getListOfProducts(Integer id) {
        DomainResponse domainResponse = productService.getListOfProducts(id);
        log.info("Response returned from Redsky API = {}", domainResponse);
        PriceInfo priceInfo = priceInfoRepository.findById(id);
        log.info("PriceInfo returned from DynamoDB = {}", priceInfo);
        return mapProductResponse.getProductDetails(domainResponse, priceInfo);
    }

    @Override
    public void updateProductDetails(FinalProduct request) {
        priceInfoRepository.updateProduct(request);
    }
}
