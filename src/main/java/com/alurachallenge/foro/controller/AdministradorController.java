package com.alurachallenge.foro.controller;


import com.alurachallenge.foro.domain.repository.AdministradorRepository;
import com.alurachallenge.foro.domain.usuarios.DatosListadoUsuario;
import com.alurachallenge.foro.domain.usuarios.Usuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administrador")
@SecurityRequirement(name = "bearer-key")
public class AdministradorController {

    @Autowired
    private AdministradorRepository administradorRepository;

    //ELIMINAR USUARIO
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity elimaUsuario(@PathVariable Long id){
        Usuario usuario = administradorRepository.getReferenceById(id);
        usuario.desactivarUsuario();
        return ResponseEntity.noContent().build();
    }

    //LISTADO USUARIO
    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuario>> listadoMedicos(@PageableDefault(size = 2) Pageable paginacion) {
        return ResponseEntity.ok(administradorRepository.findByActiveTrue(paginacion).map(DatosListadoUsuario::new));
    }
}
