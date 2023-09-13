/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.ServiApp.entidades;

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
    private Especialidad servicios;

    public Proveedor() {
    }


    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Especialidad getServicios() {
        return servicios;
    }

    public void setServicios(Especialidad servicios) {
        this.servicios = servicios;
    }
    
    public double getCostoHora() {
        return costoHora;
    }

    public void setCostoHora(double costoHora) {
        this.costoHora = costoHora;
    }
    
    
    
    
    
}
