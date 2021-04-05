package com.assessment.target.service;

import com.assessment.target.config.AppConfigService;
import com.assessment.target.dto.DomainResponse;
import com.assessment.target.exception.ProductDetailsNotFoundException;
import com.assessment.target.exception.ProductServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {

    private final AppConfigService appConfigService;
    private final RestTemplate restTemplate;

    @Override
    public DomainResponse getListOfProducts(Integer id) {
        try {
            HttpEntity<String> httpEntity = new HttpEntity<>(getHeaders());
            return restTemplate.exchange(getUrl(id), HttpMethod.GET, httpEntity, DomainResponse.class).getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("Failed to get productDetails from REDSKY API for id = {} with status = {}", id, e.getStatusCode());
            throw new ProductDetailsNotFoundException("ProductDetails not found");
        } catch (Exception e) {
            log.error("Failed to get productDetails from REDSKY API for id = {}", id, e);
            throw new ProductServiceException(e.getMessage());
        }
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private String getUrl(Integer id) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(appConfigService.getRedskyUrl())
                .path(pathVariables.get("id"))
                .queryParam("excludes", "taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics")
                .queryParam("key", "candidate");

        return uriComponentsBuilder.build().toUriString();
    }
}
