/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.ServiApp.servicios;

import com.egg.ServiApp.entidades.Calificacion;
import com.egg.ServiApp.repositorio.calificacionRepositorio;
import excepciones.miException;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import static org.hibernate.criterion.Projections.id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marco
 */
@Service
public class calificacionServicio {
    
    @Autowired
    private calificacionRepositorio califRepo;
    
    @Transactional
    public void crearCalificacion(String contenido, double puntuacion) throws miException{
        
        validar(contenido, puntuacion);
        
        Calificacion calif = new Calificacion();
        
        calif.setContenido(contenido);
        calif.setPuntuacion(puntuacion);
        
        califRepo.save(calif);
    }
    
    public List<Calificacion> listarCalificaciones(String id){
        
        List<Calificacion> calif = null;
        Optional<Calificacion> respuesta = califRepo.findById(id);
        
        if (respuesta.isPresent()) {
             calif=(List<Calificacion>) respuesta.get();
        }
        return calif;
    }
    
    @Transactional
    public void modificarCalificacion(String id, String contenido, double puntuacion){
        
        Calificacion calif = null;
        Optional<Calificacion> respuesta = califRepo.findById(id);
        
        if (respuesta.isPresent()) {
             calif = respuesta.get();
             calif.setContenido(contenido);
             calif.setPuntuacion(puntuacion);
             
             califRepo.save(calif);
        }
    }
        
    public Calificacion getOne(String id){return califRepo.getOne(id);}
    
    @Transactional
    public void eliminarCalificacion(String id){
        califRepo.deleteById(id);
    }
    
    private void validar(String contenido, double puntuacion) throws miException{
        
        if (contenido.isEmpty() || contenido==null) {
            throw new miException("El contenido no puede estar vacio /n");
        }
        if (puntuacion > 5 || puntuacion < 0) {
            throw new miException("Seleccione un numero valido entre 0 y 5 /n");
        }
    }
}
