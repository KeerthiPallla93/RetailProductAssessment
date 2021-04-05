package com.assessment.target.repository;

import com.assessment.target.data.PriceInfo;
import com.assessment.target.dto.FinalProduct;

public interface PriceInfoRepository {

    PriceInfo findById(Integer id);

    void updateProduct(FinalProduct request);
}
