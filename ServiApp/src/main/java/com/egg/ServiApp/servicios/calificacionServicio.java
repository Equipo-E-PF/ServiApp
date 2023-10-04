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
    
    @Autowired
    private trabajoRepositorio tr;

    @Transactional
    public void crearCalificacion(String trabajoId, String contenido, double puntuacion) throws miException {
        Trabajo trabajo = tr.getOne(trabajoId);
        trabajo.getCalificacion().setContenido(contenido);
        trabajo.getCalificacion().setPuntuacion(puntuacion);

        tr.save(trabajo);
        
    }

    public List<Calificacion> listarCalificaciones() {

        return califRepo.findAll();
    }

    @Transactional
    public void modificarCalificacion(String id, String contenido, double puntuacion) throws miException {

        validar(contenido, puntuacion);
        Optional<Calificacion> respuesta = califRepo.findById(id);

        if (respuesta.isPresent()) {
            Calificacion calif = respuesta.get();
            calif.setContenido(contenido);
            calif.setPuntuacion(puntuacion);

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
