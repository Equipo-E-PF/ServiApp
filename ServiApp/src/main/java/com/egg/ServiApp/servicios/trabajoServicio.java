package com.egg.ServiApp.servicios;

import com.egg.ServiApp.entidades.Calificacion;
import com.egg.ServiApp.entidades.Proveedor;
import com.egg.ServiApp.entidades.Trabajo;
import com.egg.ServiApp.entidades.Usuario;
import com.egg.ServiApp.repositorio.trabajoRepositorio;
import com.egg.ServiApp.enumeraciones.Estado;
import excepciones.miException;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ale y Choy
 */
@Service
public class trabajoServicio {

    @Autowired
    private trabajoRepositorio tr;

    @Transactional
    public void crearTrabajo(Usuario usuario, Proveedor proveedor) throws miException {
        validar(usuario, proveedor);
        Trabajo trabajo = new Trabajo();
        trabajo.setEstado(Estado.PENDIENTE);
        trabajo.setUsuario(usuario);
        trabajo.setProveedor(proveedor);
        trabajo.setCalificacion(new Calificacion());

        tr.save(trabajo);
    }

    @Transactional
    public void modificarEstado(String id, Estado estado) {
        Optional<Trabajo> trabajoOptional = tr.findById(id);

        if (trabajoOptional.isPresent()) {
            Trabajo trabajo = trabajoOptional.get();
            trabajo.setEstado(estado);
            tr.save(trabajo);
        }
    }
    
    @Transactional
    public void terminarTrabajo(String id, Estado estado, Calificacion calificacion) {
        Optional<Trabajo> trabajoOptional = tr.findById(id);

        if (trabajoOptional.isPresent()) {
            Trabajo trabajo = trabajoOptional.get();
            trabajo.setEstado(estado);
            trabajo.setCalificacion(calificacion);
            tr.save(trabajo);
        }
    }

    public List<Trabajo> listarTrabajos() {
        return tr.findAll();
    }

    public Trabajo getTrabajo(String id) {
        return tr.findById(id).orElse(null);
    }

    @Transactional
    public void eliminarTrabajo(String id) {
        tr.deleteById(id);
    }

    private void validar(Usuario usuario, Proveedor proveedor) throws miException {
        if (usuario == null) {
            throw new miException("El usuario del trabajo no puede ser nulo");
        }

        if (proveedor == null) {
            throw new miException("Los proveedores del trabajo no pueden ser nulos");
        }
    }

    //Asociar Calificación 
    @Transactional
    public void asociarCalificacion(String trabajoId, String contenido) throws miException {
        Trabajo trabajo = tr.getOne(trabajoId);
        Calificacion calificacion = new Calificacion();
        calificacion.setContenido(contenido);

        // Aquí podrías realizar alguna validación adicional si es necesario
        trabajo.setCalificacion(calificacion);
        tr.save(trabajo);
    }
}
