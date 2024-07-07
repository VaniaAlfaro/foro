package com.alurachallenge.foro.infra.errores;

public class ValidacionDeIntegridad extends  RuntimeException {
    public ValidacionDeIntegridad(String idDePacienteNoEncontrado){
        super(idDePacienteNoEncontrado);
    }
}
