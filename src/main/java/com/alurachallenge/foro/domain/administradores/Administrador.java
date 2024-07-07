package com.alurachallenge.foro.domain.administradores;

import com.alurachallenge.foro.domain.usuarios.Perfiles;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

//CREAR TABLA EN BASE DE DATOS
@Table(name = "administradores")
@Entity(name = "Administradores")

public class Administrador implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;          //IDENTIFICADOR DEL ADMINISTRADOR
    private String nombre;   //NOMBRE DEL ADMINISTRADOR
    private String email;   //LOGIN DEL ADMINISTRADOR
    private String clave;  //CONTRASEÑA DEL ADMINISTRADOR
    private String administrador = "000000";

    @Enumerated(EnumType.STRING)
    private Perfiles perfiles; //STATUS DEL ADMINISTRADOR

    //GETTERS
    public Long getId() {return id;}
    public String getNombre() {return nombre;}
    public String getEmail() {return email;}
    public String getClave() {return clave;}
    public String getAdministrador() {return administrador;}
    public Perfiles getPerfiles() {return perfiles;}

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
}
