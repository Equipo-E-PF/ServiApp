package com.egg.ServiApp.entidades;

import com.egg.ServiApp.enumeraciones.Rol;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Juanp
 */
@Entity
public class Usuario {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    protected String id;

    protected String nombre;
    protected String email;
    protected String Password;
    protected Long telefono;
    protected boolean baja;
    
    @OneToOne
    protected Imagen imagen;

    @Enumerated(EnumType.STRING)
    protected Rol rol;

    public Usuario() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
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

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

   
    public Proveedor convertirEnProveedor() {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(this.getNombre());
        proveedor.setEmail(this.getEmail());
        proveedor.setPassword(this.getPassword());
        proveedor.setTelefono(this.getTelefono());
        proveedor.setBaja(this.isBaja());
        proveedor.setRol(Rol.PROVEEDOR);
        proveedor.setImagen(this.imagen);
        return proveedor;
    }

}
