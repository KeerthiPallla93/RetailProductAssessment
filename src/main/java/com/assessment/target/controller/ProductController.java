package com.assessment.target.controller;

import com.assessment.target.dto.FinalProduct;
import com.assessment.target.manager.ProductManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;

@RestController
@RequestMapping("/productDetails")
@Log4j2
@RequiredArgsConstructor
public class ProductController {

    private final ProductManager productManager;

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<FinalProduct> getProductDetails(@PathVariable("id") Integer id) {
        Instant start = Instant.now();
        log.info("Requested ProductDetails for id={}", id);
        FinalProduct finalProduct = productManager.getListOfProducts(id);
        log.info("Retrieved Product Details for given id = {} within {} time", id, Duration.between(start, Instant.now()).toMillis());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(finalProduct);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public void updateProductDetails(@RequestBody FinalProduct request) {
        log.info("Updated ProductDetails for id={}", request.getId());
        productManager.updateProductDetails(request);
    }
}
