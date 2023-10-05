package com.egg.ServiApp.servicios;

import com.egg.ServiApp.entidades.Calificacion;
import com.egg.ServiApp.entidades.Trabajo;
import com.egg.ServiApp.repositorio.calificacionRepositorio;
import com.egg.ServiApp.repositorio.trabajoRepositorio;
import excepciones.miException;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
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
    public Calificacion crearCalificacion() throws miException {
        Calificacion calif = new Calificacion();
        calif.setContenido(null);
        calif.setPuntuacion(0);
        
        califRepo.save(calif);
                
        return calif;
    }

    public List<Calificacion> listarCalificaciones() {

        return califRepo.findAll();
    }

    @Transactional
    public void modificarCalificacion(String id, String contenido, double puntuacion) throws miException {
        System.out.println("11111");
        validar(contenido, puntuacion);
        Optional<Calificacion> respuesta = califRepo.findById(id);
        System.out.println("22222"+" "+respuesta.toString()+" "+id);
        if (respuesta.isPresent()) {
            Calificacion calif = respuesta.get();
            calif.setContenido(contenido);
            calif.setPuntuacion(puntuacion);
            System.out.println("3333");
            califRepo.save(calif);
        }
    }

    @Transactional
    public void censurarCalificacion(String id) throws miException {

        Optional<Calificacion> respuesta = califRepo.findById(id);

        if (respuesta.isPresent()) {
            Calificacion calif = respuesta.get();
            calif.setContenido("Contenido censurado");

            califRepo.save(calif);
        }
    }

    public Calificacion getOne(String id) {
        return califRepo.getOne(id);
    }

    @Transactional
    public void eliminarCalificacion(String id) {
        califRepo.deleteById(id);
    }

    private void validar(String contenido, double puntuacion) throws miException {

        if (contenido == null || contenido.isEmpty()) {
            throw new miException("El contenido no puede estar vacio /n");
        }
        if (puntuacion > 5 || puntuacion < 0) {
            throw new miException("Seleccione un numero valido entre 0 y 5 /n");
        }
    }
}
