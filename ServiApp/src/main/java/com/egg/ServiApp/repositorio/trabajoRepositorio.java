/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.ServiApp.repositorio;

import com.egg.ServiApp.entidades.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marco
 */
@Repository
public interface trabajoRepositorio extends JpaRepository<Trabajo, String>{
    
    
    @Query("SELECT a FROM Trabajo a WHERE a.calificacion = :calificacion")
    public Trabajo buscarPorCalificacion(@Param("calificacion") String calificacion);
        
    @Query("SELECT a FROM Trabajo a WHERE a.id = :id")
    public Trabajo buscarPorId(@Param ("id")String id);
    
    @Query("SELECT a FROM Trabajo a WHERE a.proveedor = :proveedor")
    public Trabajo buscarPorProveedor(@Param ("proveedor")String proveedor);
    
    @Query("SELECT a FROM Trabajo a WHERE a.estado = :estado")
    public Trabajo buscarPorEstado(@Param ("estado")String estado);
}
