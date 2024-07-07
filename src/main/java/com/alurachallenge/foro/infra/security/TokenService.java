package com.alurachallenge.foro.infra.security;

import com.alurachallenge.foro.domain.repository.UsuarioRepository;
import com.alurachallenge.foro.domain.usuarios.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256("123456");
            return  JWT.create()
                    .withIssuer("foroAlura")
                    .withSubject(usuario.getEmail())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFehcaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error en Genera Token");
        }
    }

    public String getSubject(String token) {
        if (token == null){
            throw new IllegalArgumentException("El token es nulo.");
        }
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            String username = decodedJWT.getSubject();
            if (username == null) {
                throw new IllegalArgumentException("Token no válido: Asunto no encontrado");
            }
            Usuario usuario = (Usuario) usuarioRepository.findByEmail(username);
            if (usuario == null) {
                throw new IllegalArgumentException("Usuario no encontrado por nombre de usuario: " + username);
            }

            Algorithm algorithm = Algorithm.HMAC256("123456");
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("foroAlura")
                    .build()
                    .verify(token);
            return verifier.getSubject();
        } catch (JWTVerificationException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Token no válido: " + e.getMessage(), e);
        }
    }

    private Instant generarFehcaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

}
