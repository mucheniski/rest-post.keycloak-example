package com.example.restpost.keycloak.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserRealmDTO {

    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    @JsonProperty("enabled")
    private Boolean enabled;

    @JsonProperty("emailVerified")
    private Boolean emailVerified;

    @JsonProperty("requiredActions")
    private List<String> requiredActions = new ArrayList<>();

}
