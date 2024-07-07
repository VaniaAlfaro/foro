package com.alurachallenge.foro.controller;

import com.alurachallenge.foro.domain.repository.UsuarioRepository;
import com.alurachallenge.foro.domain.usuarios.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping ("/usuario")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    //REGISTRAR USUARIO
    @PostMapping
    @Transactional
    public ResponseEntity <DatosRespuestaUsuario> registrarUsuario (@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario, UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistroUsuario));
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getClave(),
                usuario.getPerfiles(),
                usuario.isActive());
        URI url = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }

    //ACTUALIZAR USUARIO
    @PutMapping
    @Transactional
    public ResponseEntity actualizarUsuario (@RequestBody @Valid DatosActualizarDatosUsuario datosActualizarDatosUsuario){
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizarDatosUsuario.id());
        usuario.ActualizarDatosUsuario(datosActualizarDatosUsuario);
        return ResponseEntity.ok(new DatosRespuestaUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getClave(),
                usuario.getPerfiles(),
                usuario.isActive()));
    }
}
