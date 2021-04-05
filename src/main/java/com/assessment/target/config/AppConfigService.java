package com.assessment.target.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Log4j2
public class AppConfigService {

    @Value("${rest.url}")
    private String redskyUrl;

    @Value("${aws.table}")
    private String awsTableName;

}
