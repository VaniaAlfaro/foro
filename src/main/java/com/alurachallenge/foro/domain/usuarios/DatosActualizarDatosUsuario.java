package com.alurachallenge.foro.domain.usuarios;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarDatosUsuario(
        Long id,
        String nombre,
        String email,
        String clave,
        Perfiles perfiles,
        Boolean active){
}
