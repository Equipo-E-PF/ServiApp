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
    public void crearEspecialidad(String nombre) throws miException {

        validar(nombre);

        Especialidad especialidad = new Especialidad();

        especialidad.setNombre(nombre);
        er.save(especialidad);
    }

    @Transactional
    public void modificarEspecialidad(String id, String nombre) throws miException {
        
        validar(nombre);
        Especialidad especialidad = er.buscarPorId(id);
        especialidad.setNombre(nombre);

        er.save(especialidad);
    }

    public List<Especialidad> listarEspecialidades() {
        return er.findAll();
    }

    @Transactional
    public void eliminarEspecialidadId(String id) {

        er.deleteById(id);

    }

    protected void validar(String nombre) throws miException {

        if (nombre.isEmpty() || nombre == null) {
            throw new miException("El nombre no puede ser nulo");
        }
    }

    public Especialidad buscarPorNombre(String especialidad) {
        return er.buscarPorNombre(especialidad);
    }

}
