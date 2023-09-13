/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.ServiApp.repositorio;

import com.egg.ServiApp.entidades.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 *
 * @author marco
 */
@Repository
public interface proveedorRepositorio extends JpaRepository<Proveedor, String>{
    
    
    @Query("SELECT a FROM Proveedor a WHERE a.servicios.nombre = :servicio")
    public Proveedor buscarPorServicio(@Param("servicio") String servicio);
    
    @Query("SELECT a FROM Proveedor a WHERE a.puntuacion = :puntuacion")
    public Proveedor buscarPorPuntuacion(@Param ("puntuacion")String puntuacion);
    
    @Query("SELECT a FROM Proveedor a WHERE a.costoHora = :costoHora")
    public Proveedor buscarPorCostoHora(@Param ("costoHora")String costoHora);
}
