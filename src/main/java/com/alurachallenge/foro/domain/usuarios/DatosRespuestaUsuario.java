package com.alurachallenge.foro.domain.usuarios;

public record DatosRespuestaUsuario(
        Long id,
        String nombre,
        String email,
        String clave,
        Perfiles perfiles,
        Boolean active){
}
