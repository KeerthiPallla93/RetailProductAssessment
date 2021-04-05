package com.assessment.target.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.assessment.target.data.PriceInfo;
import com.assessment.target.dto.DomainResponse;
import com.assessment.target.dto.FinalProduct;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static com.assessment.target.util.TestUtils.getCurrentPrice;
import static com.assessment.target.util.TestUtils.getFinalProduct;
import static com.assessment.target.util.TestUtils.getPriceInfo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PriceInfoRepositoryImplTest {

    @InjectMocks
    PriceInfoRepositoryImpl priceInfoRepository;
    @Mock
    DynamoDBMapper dynamoDBMapper;
    @Mock
    DynamoDBMapperConfig dynamoDBMapperConfig;
    @Captor
    ArgumentCaptor<FinalProduct> finalProductCaptor;
    @Captor
    ArgumentCaptor<DynamoDBMapperConfig> dynamoDBMapperCaptor;

    @Test
    public void returnPriceInfo_fromDynamoDB() {
        when(dynamoDBMapper.load(PriceInfo.class, 11)).thenReturn(getPriceInfo());
        PriceInfo response = priceInfoRepository.findById(11);
        assertNotNull(response);
    }

    @Test
    public void returnNull_whenIdNotFound() {
        when(dynamoDBMapper.load(PriceInfo.class, 12)).thenReturn(null);
        PriceInfo response = priceInfoRepository.findById(12);
        assertNull(response);
    }

    @Test
    public void updatePriceInfo_toDynamoDB() {
        FinalProduct finalProduct = getFinalProduct(132424, getCurrentPrice());
        priceInfoRepository.updateProduct(finalProduct);
        verify(dynamoDBMapper, times(1)).save(finalProduct, dynamoDBMapperConfig);
        verify(dynamoDBMapper).save(finalProductCaptor.capture(), dynamoDBMapperCaptor.capture());
        FinalProduct finalProductValue = finalProductCaptor.getValue();
        assertEquals(finalProduct, finalProductValue);
    }

}
