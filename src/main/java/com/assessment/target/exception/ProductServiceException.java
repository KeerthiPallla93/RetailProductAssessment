package com.assessment.target.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ProductServiceException extends RuntimeException {

    public ProductServiceException(String message) {
        super(message);
    }

}
