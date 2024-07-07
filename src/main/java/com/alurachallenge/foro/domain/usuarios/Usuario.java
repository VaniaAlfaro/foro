package com.alurachallenge.foro.domain.usuarios;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

//SECURITY PASSWORD
//341f7845-4603-45c1-b568-b59ffe6cba23

//CREAR TABLA EN BASE DE DATOS
@Table(name = "usuarios")
@Entity(name = "Usuarios")

public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;          //IDENTIFICADOR DE USUARIO
    private String nombre;   //NOMBRE DEL USUARIO
    private String email;   //LOGIN DEL USUARIO
    private String clave;  //CONTRASEÑA DEL USUARIO
    private boolean active = true;

    @Enumerated(EnumType.STRING)
    private Perfiles perfiles; //STATUS DEL USUARIO

    //Getters
    public Long getId() {return id;}
    public String getNombre() {return nombre;}
    public String getEmail() {return email;}
    public String getClave() {return clave;}
    public Perfiles getPerfiles() {return perfiles;}
    public boolean isActive() {return active;}

    //Autentificar Usuario
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {return List.of(new SimpleGrantedAuthority("ROLE_USER"));}
    @Override
    public String getPassword() {return clave;}
    @Override
    public String getUsername() {return email;}
    @Override
    public boolean isAccountNonExpired() {return true;}
    @Override
    public boolean isAccountNonLocked() {return true;}
    @Override
    public boolean isCredentialsNonExpired() {return true;}
    @Override
    public boolean isEnabled() {return true;}

    //Funciones
    public Usuario(DatosRegistroUsuario datosRegistroUsuario){
        this.nombre = datosRegistroUsuario.nombre();
        this.email = datosRegistroUsuario.email();
        this.clave = datosRegistroUsuario.clave();
        this.perfiles = datosRegistroUsuario.perfiles();
        this.active = true;
    }

    public void ActualizarDatosUsuario (DatosActualizarDatosUsuario datosActualizarDatosUsuario){
        //NOMBRE
        if(datosActualizarDatosUsuario.nombre() != null){
            this.nombre = datosActualizarDatosUsuario.nombre();
        }
        //EMAIL
        if(datosActualizarDatosUsuario.email() != null){
            this.email = datosActualizarDatosUsuario.email();
        }
        //CLAVE
        if(datosActualizarDatosUsuario.clave() != null){
            this.clave = datosActualizarDatosUsuario.clave();
        }
        //PERFILES
        if(datosActualizarDatosUsuario.perfiles() != null){
            this.perfiles = datosActualizarDatosUsuario.perfiles();
        }
        //ACTIVO
        if(datosActualizarDatosUsuario.active() != null){
            this.active = datosActualizarDatosUsuario.active();
        }
    }

    public void desactivarUsuario(){this.active = false;}
}
