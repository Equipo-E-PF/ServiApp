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
    private Integer costoHora;
    
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

    public Especialidad getServicios() {
        return especialidad;
    }

    public void setServicios(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
    
    public double getCostoHora() {
        return costoHora;
    }

    public void setCostoHora(Integer costoHora) {
        this.costoHora = costoHora;
    }
    
    
    
    
    
}
