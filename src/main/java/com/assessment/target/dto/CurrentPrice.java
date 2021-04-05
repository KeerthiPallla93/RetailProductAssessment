package com.assessment.target.dto;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Data;

@Data
@DynamoDBDocument
public class CurrentPrice {
    private Double value;
    private String currency_code;
}
