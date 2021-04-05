package com.assessment.target.mapper;

import com.assessment.target.data.PriceInfo;
import com.assessment.target.dto.FinalProduct;
import com.assessment.target.dto.DomainResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapProductResponse {

    @Mapping(target = "id", source = "domainResponse.product.item.tcin")
    @Mapping(target = "name", source = "domainResponse.product.item.product_description.title")
    FinalProduct getProductDetails(DomainResponse domainResponse, PriceInfo priceInfo);
}
