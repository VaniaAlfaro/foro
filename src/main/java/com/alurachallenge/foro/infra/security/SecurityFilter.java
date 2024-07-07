package com.alurachallenge.foro.infra.security;

import com.alurachallenge.foro.domain.repository.UsuarioRepository;
import com.alurachallenge.foro.domain.usuarios.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try{
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")){
                String token = authHeader.replace("Bearer", "");
                String subject = tokenService.getSubject(token);

                if (subject != null){
                    //TOKEN VALIDO
                    Usuario usuario = (Usuario) usuarioRepository.findByEmail(subject);
                    if (usuario != null){
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                        usuario.getAuthorities();
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }else{
                        throw new IllegalArgumentException("Usuario no encontrado por nombre de usuario: " + subject);
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            logger.error("Error de autenticación: " + e.getMessage(), e);
        }
        filterChain.doFilter(request, response);
    }
}
