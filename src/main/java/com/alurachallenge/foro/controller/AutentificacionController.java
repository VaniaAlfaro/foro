package com.alurachallenge.foro.controller;

import com.alurachallenge.foro.infra.security.DatosJWTToken;
import com.alurachallenge.foro.domain.usuarios.DatosAutentificarUsuario;
import com.alurachallenge.foro.domain.usuarios.Usuario;
import com.alurachallenge.foro.infra.security.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutentificacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    @Transactional
    public ResponseEntity autentificarUsuario (@RequestBody @Valid DatosAutentificarUsuario datosAutentificarUsuario){
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                datosAutentificarUsuario.email(),
                datosAutentificarUsuario.clave());
        System.out.println("AUTENTIFICACION:" + " " + authentication);

        var usuarioAuth = authenticationManager.authenticate(authentication);
        System.out.println("USUARIO AUTENTIFICADO" + usuarioAuth);

        var JWTtoken = tokenService.generarToken((Usuario) usuarioAuth.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
