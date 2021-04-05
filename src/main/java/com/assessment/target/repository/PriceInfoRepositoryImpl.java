package com.assessment.target.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.assessment.target.data.PriceInfo;
import com.assessment.target.dto.FinalProduct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
@Log4j2
public class PriceInfoRepositoryImpl implements PriceInfoRepository {

    @Resource
    DynamoDBMapper dynamoDBMapper;

    @Autowired
    private DynamoDBMapperConfig defaultMapperConfig;


    @Override
    public PriceInfo findById(Integer id) {
        PriceInfo priceInfo = dynamoDBMapper.load(PriceInfo.class, id);
        if (priceInfo == null) {
            log.info("Price Info not found for id = {} in DB ", id);
        }
        return priceInfo;
    }

    @Override
    public void updateProduct(FinalProduct request) {
        dynamoDBMapper.save(request, defaultMapperConfig);
        log.info("Product Details are saved in DB");
    }
}
