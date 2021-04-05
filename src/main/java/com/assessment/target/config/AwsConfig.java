package com.assessment.target.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    @Autowired
    private AppConfigService appConfigService;

    private final DynamoDBMapperConfig.TableNameResolver TABLE_NAME_RESOLVER = (aClass, dynamoDBMapperConfig) -> appConfigService.getAwsTableName();

    @Bean(name = "dynamoDBMapper")
    public DynamoDBMapper dynamoDBMapperLocal() {
        Regions region = Regions.US_EAST_2;
        DynamoDBMapperConfig dynamoDBMapperConfig = new DynamoDBMapperConfig.Builder().withTableNameResolver(TABLE_NAME_RESOLVER).build();
        AmazonDynamoDBClient amazonDynamoDBClient = dynamoClient(region);
        return new DynamoDBMapper(amazonDynamoDBClient, dynamoDBMapperConfig);
    }

    @Bean
    public AmazonDynamoDBClient dynamoClient(Regions region) {
        return (AmazonDynamoDBClient) AmazonDynamoDBClientBuilder.standard()
                .withRegion(region)
                .build();
    }

    @Bean
    public DynamoDBMapperConfig dynamoDBMapperConfig() {
        return new DynamoDBMapperConfig.Builder()
                .build();
    }

}
