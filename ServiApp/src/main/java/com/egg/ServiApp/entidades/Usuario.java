/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.ServiApp.entidades;

import com.egg.ServiApp.enumeraciones.Rol;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

/**
 *
 * @author Juanp
 */
@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
    
    protected String nombre;
    protected String email;
    protected String Password;
    protected int telefono;
    protected boolean baja;
    
    @Enumerated(EnumType.STRING)
    protected Rol rol;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "imagen", columnDefinition = "BLOB")
    protected byte[] imagen;
    
    

    public Usuario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public boolean isBaja() {
        return baja;
    }

    public void setBaja(boolean baja) {
        this.baja = baja;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    
    
    
    
    
    
}
