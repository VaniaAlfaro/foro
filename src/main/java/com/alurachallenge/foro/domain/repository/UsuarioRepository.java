package com.alurachallenge.foro.domain.repository;

import com.alurachallenge.foro.domain.usuarios.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    UserDetails findByClave(String clave);
    UserDetails findByEmail(String email);
}
