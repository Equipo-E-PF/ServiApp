/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.ServiApp.repositorio;

import com.egg.ServiApp.entidades.Servicio;
import com.egg.ServiApp.entidades.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author marco
 */
public interface servicioRepositorio extends JpaRepository<Servicio, String>{
    
    
      @Query("SELECT a FROM Servicio a WHERE a.nombre = :nombre")
    public Servicio buscarPorNombre(@Param("nombre") String nombre);
        
    @Query("SELECT a FROM Servicio a WHERE a.id = :id")
    public Servicio buscarPorId(@Param ("id")String id);
    
    
}
