package com.example.restpost.keycloak.domain;

import lombok.Data;

@Data
public class UserKeycloakModel {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
