/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.ServiApp.repositorio;

import com.egg.ServiApp.entidades.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author marco
 */
public interface proveedorRepositorio extends JpaRepository<Proveedor, String>{
    
}
