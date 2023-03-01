package com.example.restpost.keycloak.respository;

import com.example.restpost.keycloak.domain.UsuarioOrigem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioOrigemRepository extends JpaRepository<UsuarioOrigem, Integer> {
}
