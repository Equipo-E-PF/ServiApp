package com.egg.ServiApp.entidades;

import com.egg.ServiApp.enumeraciones.Rol;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author Juanp
 */
@Entity
public class Proveedor extends Usuario implements Serializable {

    private double puntuacion;
    private double costoHora;

    @OneToOne
    private Especialidad especialidad;

    public Proveedor() {
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public double getCostoHora() {
        return costoHora;
    }

    public void setCostoHora(double costoHora) {
        this.costoHora = costoHora;
    }

    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre);
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public void setPassword(String Password) {
        super.setPassword(Password);
    }

    @Override
    public void setTelefono(Long telefono) {
        super.setTelefono(telefono);
    }

    @Override
    public void setRol(Rol rol) {
        super.setRol(rol);
    }

    @Override
    public void setImagen(Imagen imagen) {
        super.setImagen(imagen);
    }

    @Override
    public void setBaja(boolean baja) {
        super.setBaja(baja);
    }

}
