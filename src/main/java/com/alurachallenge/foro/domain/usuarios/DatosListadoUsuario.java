package com.alurachallenge.foro.domain.usuarios;

public record DatosListadoUsuario(
        Long id,
        String nombre,
        String email,
        Perfiles perfiles,
        Boolean active){

    public DatosListadoUsuario(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPerfiles(),
                usuario.isActive());
    }
}
