package com.example.restpost.keycloak.service;

import com.example.restpost.keycloak.domain.UsuarioOrigem;
import com.example.restpost.keycloak.dto.TokenCapitalGlobalDTO;
import com.example.restpost.keycloak.dto.UserRealmDTO;
import com.example.restpost.keycloak.properties.RealmCapitalGlobalProperties;
import com.example.restpost.keycloak.respository.UsuarioOrigemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Slf4j
@Service
public class UsuarioOrigemService {

    @Autowired
    private UsuarioOrigemRepository usuarioOrigemRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RealmCapitalGlobalProperties realmCapitalGlobalProperties;

    public List<UsuarioOrigem> findAll() {
        return usuarioOrigemRepository.findAll();
    }

    public UsuarioOrigem save(UsuarioOrigem usuarioOrigem) {
        return usuarioOrigemRepository.save(usuarioOrigem);
    }

    public void saveOnRealm() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        TokenCapitalGlobalDTO tokenCapitalGlobalDTO = obterToken();
        log.info("ACCESS TOKEN - {}", tokenCapitalGlobalDTO.getAccessToken());

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(tokenCapitalGlobalDTO.getAccessToken());

        UserRealmDTO userRealmDTO = populaUsuario();

        ResponseEntity<UserRealmDTO> response = restTemplate.exchange(
                realmCapitalGlobalProperties.getEndpoints().get("insert-user"),
                HttpMethod.POST,
                new HttpEntity<>(userRealmDTO, headers),
                UserRealmDTO.class
        );

        String teste = "";

    }

    private TokenCapitalGlobalDTO obterToken() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", realmCapitalGlobalProperties.getGrantType());
        map.add("client_id", realmCapitalGlobalProperties.getClientId());
        map.add("client_secret", realmCapitalGlobalProperties.getClientSecret());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestBody = new HttpEntity<>(map, headers);

        return restTemplate.postForObject(realmCapitalGlobalProperties.getEndpoints().get("token"), requestBody, TokenCapitalGlobalDTO.class);

    }

    private static UserRealmDTO populaUsuario() {
        UserRealmDTO userRealmDTO = new UserRealmDTO();
        userRealmDTO.setUsername("70468328033");
        userRealmDTO.setEmail("70468328033@gmail.com");
        userRealmDTO.setEnabled(true);
        userRealmDTO.setEmailVerified(true);
        userRealmDTO.getRequiredActions().add("UPDATE_PASSWORD");
        return userRealmDTO;
    }

}
