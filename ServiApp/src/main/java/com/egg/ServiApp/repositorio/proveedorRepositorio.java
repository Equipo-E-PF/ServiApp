/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.ServiApp.repositorio;

import com.egg.ServiApp.entidades.Proveedor;
import com.egg.ServiApp.entidades.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 *
 * @author marco
 */
public interface proveedorRepositorio extends JpaRepository<Proveedor, String>{
    
    
    @Query("SELECT a FROM Proveedor a WHERE a.servicio = :servicio")
    public Proveedor buscarPorServicio(@Param("servicio") String servicio);
        
    @Query("SELECT a FROM Proveedor a WHERE a.id = :id")
    public Proveedor buscarPorId(@Param ("id")String id);
    
    @Query("SELECT a FROM Proveedor a WHERE a.puntuacion = :puntuacion")
    public Proveedor buscarPorPuntuacion(@Param ("puntuacion")String puntuacion);
    
    @Query("SELECT a FROM Proveedor a WHERE a.costoHora = :costoHora")
    public Proveedor buscarPorCostoHora(@Param ("costoHora")String costoHora);
}
