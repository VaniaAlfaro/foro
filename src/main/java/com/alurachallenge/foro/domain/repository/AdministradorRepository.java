package com.alurachallenge.foro.domain.repository;

import com.alurachallenge.foro.domain.usuarios.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Usuario, Long> {
    Page<Usuario> findByActiveTrue(Pageable paginacion);
}
