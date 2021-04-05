package com.assessment.target.manager;

import com.assessment.target.dto.FinalProduct;

public interface ProductManager {

    FinalProduct getListOfProducts(Integer id);

    void updateProductDetails(FinalProduct request);
}
