package com.egg.ServiApp.servicios;

import com.egg.ServiApp.entidades.Calificacion;
import com.egg.ServiApp.entidades.Proveedor;
import com.egg.ServiApp.entidades.Trabajo;
import com.egg.ServiApp.entidades.Usuario;
import com.egg.ServiApp.repositorio.trabajoRepositorio;
import com.egg.ServiApp.enumeraciones.Estado;
import com.egg.ServiApp.repositorio.usuarioRepositorio;
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
    @Autowired
    private calificacionServicio cs;
    @Autowired
    private usuarioServicio us;
    @Autowired
    private usuarioRepositorio ur;

    @Transactional
    public void crearTrabajo(String idU, String idP, String descripcion) throws miException {
        Usuario usuario = ur.usuarioPorId(idU);
        Proveedor proveedor = ur.proveedorPorId(idP);
        validar(usuario, proveedor);
        Trabajo trabajo = new Trabajo();
        trabajo.setDescripcion(descripcion);
        trabajo.setEstado(Estado.PENDIENTE);
        trabajo.setUsuario(usuario);
        trabajo.setProveedor(proveedor);
        trabajo.setCalificacion(cs.crearCalificacion(null, 0));

        tr.save(trabajo);
    }

    @Transactional
    public void modificarEstado(String id, Estado estado) throws miException {
        Optional<Trabajo> trabajoOptional = tr.findById(id);

        if (trabajoOptional.isPresent()) {
            Trabajo trabajo = trabajoOptional.get();
            trabajo.setEstado(estado);
            tr.save(trabajo);
        }
    }

    @Transactional
    public void terminarTrabajo(String id, Estado estado, String contenidoPuntuacion, double puntuacion) throws miException {
        Optional<Trabajo> trabajoOptional = tr.findById(id);

        if (trabajoOptional.isPresent()) {
            Trabajo trabajo = trabajoOptional.get();
            trabajo.setEstado(estado);
            cs.modificarCalificacion(trabajoOptional.get().getCalificacion().getId(), contenidoPuntuacion, puntuacion);
            us.calificarProveedor(trabajoOptional.get().getProveedor().getId(), puntuacion);
            tr.save(trabajo);
        }
    }

    public List<Trabajo> listarTrabajos() {
        return tr.findAll();
    }
    
    public List<Trabajo> listarTrabajoPorProveedor(String id){
        return tr.listarPorProveedor(id);
    }
    
    public List<Trabajo> listarTrabajoCalificado(){
        return tr.TrabajosCalificados();
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
            throw new miException("El proveedor del trabajo no pueden ser nulos");
        }
    }

    @Transactional
    public void aceptarTrabajo(String id) {
        Optional<Trabajo> trabajoOptional = tr.findById(id);

        if (trabajoOptional.isPresent()) {
            Trabajo trabajo = trabajoOptional.get();
            trabajo.setEstado(Estado.ACEPTADO);
            tr.save(trabajo);
        }
    }

    @Transactional
    public void rechazarTrabajo(String id) {
        Optional<Trabajo> trabajoOptional = tr.findById(id);

        if (trabajoOptional.isPresent()) {
            Trabajo trabajo = trabajoOptional.get();
            trabajo.setEstado(Estado.CANCELADO);
            tr.save(trabajo);
        }
    }

    public List<Trabajo> listarTrabajosPendientes() {
        return tr.findByEstado(Estado.PENDIENTE);
    }

    public List<Trabajo> listarTrabajosAceptados() {
        return tr.findByEstado(Estado.ACEPTADO);
    }

    public List<Trabajo> listarTrabajosCompletados() {
        return tr.findByEstado(Estado.FINALIZADO);
    }
    
    public List<Trabajo> TrabajoPorProveedorEstado(String id, Estado estado) {
        return tr.TrabajoPorProveedorEstado(id, estado);
    }
    
    public List<Trabajo> TrabajoCalificadosProveedor(String id) {
        return tr.TrabajoCalificadosProveedor(id);
    }
    
    
    public List<Trabajo> TrabajoPorUsuarioEstado(String id, Estado estado) {
        return tr.TrabajoPorUsuarioEstado(id, estado);
    }
    
    public List<Trabajo> TrabajoCalificadosUsuario(String id) {
        return tr.TrabajoCalificadosUsuario(id);
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
