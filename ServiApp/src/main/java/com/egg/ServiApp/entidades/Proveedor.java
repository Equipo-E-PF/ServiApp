/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.ServiApp.entidades;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author Juanp
 */

@Entity
public class Proveedor extends Usuario {
    
    private double puntuacion;
    private double costoHora;
    
    @OneToOne
    private Servicio servicios;

    public Proveedor() {
    }


    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Servicio getServicios() {
        return servicios;
    }

    public void setServicios(Servicio servicios) {
        this.servicios = servicios;
    }
    
    public double getCostoHora() {
        return costoHora;
    }

    public void setCostoHora(double costoHora) {
        this.costoHora = costoHora;
    }
    
    
    
    
    
}
