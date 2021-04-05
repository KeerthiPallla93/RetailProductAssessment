package com.assessment.target.mapper;

import com.assessment.target.dto.FinalProduct;
import org.junit.jupiter.api.Test;
import static com.assessment.target.util.TestUtils.getDomainResponse;
import static com.assessment.target.util.TestUtils.getPriceInfo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MapProductResponseImplTest {

    @Test
    public void formResponse() {
        MapProductResponseImpl mapProductResponse = new MapProductResponseImpl();
        FinalProduct finalProduct = mapProductResponse.getProductDetails(getDomainResponse(), getPriceInfo());
        assertNotNull(finalProduct);
    }

    @Test
    public void returnResponse_whenPriceInfoIsEmpty() {
        MapProductResponseImpl mapProductResponse = new MapProductResponseImpl();
        FinalProduct finalProduct = mapProductResponse.getProductDetails(getDomainResponse(), null);
        assertNotNull(finalProduct);
        assertNull(finalProduct.getCurrent_price());
    }
}
