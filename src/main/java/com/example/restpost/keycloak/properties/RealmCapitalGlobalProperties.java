package com.example.restpost.keycloak.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "realm-capital-global")
@Data
public class RealmCapitalGlobalProperties {

    private String grantType;
    private String clientId;
    private String clientSecret;
    private Map<String, String> endpoints;

}
