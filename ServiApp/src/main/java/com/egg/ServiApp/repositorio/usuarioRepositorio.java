/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.ServiApp.repositorio;

import com.egg.ServiApp.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marco
 */
@Repository
public interface usuarioRepositorio extends JpaRepository<Usuario, String>{
    
    
    @Query("SELECT a FROM Usuario a WHERE a.email = :email")
        public Usuario buscarPorEmail(@Param("email") String email);
        
    @Query("SELECT a FROM Usuario a WHERE a.id = :id")
    public Usuario buscarPorId(@Param ("id")String id);
    
    @Query("SELECT a FROM Usuario a WHERE a.rol = :rol")
    public Usuario buscarPorRol(@Param ("rol")String rol);
}
