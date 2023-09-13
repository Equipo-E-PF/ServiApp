/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.ServiApp.servicios;

import com.egg.ServiApp.entidades.Especialidad;
import com.egg.ServiApp.repositorio.especialidadRepositorio;
import excepciones.miException;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juanp
 */

@Service
public class especialidadServicio {
    
    @Autowired
    private especialidadRepositorio er;
    
    @Transactional
    public void crearEspecialidad (String nombre) throws miException{
        
        validar(nombre);
        
        Especialidad especialidad = new Especialidad();
        
        especialidad.setNombre(nombre);
        er.save(especialidad);
    }
    
    @Transactional
    public void modificarEspecialidad(String id, String nombre){
        
        Especialidad especialidad = er.buscarPorId(id);
        especialidad.setNombre(nombre);
        
        er.save(especialidad);
    }
    
    public List<Especialidad> listarEspecialidades(){
        return er.findAll();
    }
    
    @Transactional
    public void eliminarEspecialidadId(String id) throws miException{
        
        if (er.findById(id).isPresent()) {
            er.deleteById(id);
        }else{
            throw new miException("El id no existe");
        }
        
    }
    
    protected void validar(String nombre) throws miException{
        
        if (nombre.isEmpty()||nombre==null) {
            throw new miException("El nombre no puede ser nulo");
        }
    }
    
    
    
}
