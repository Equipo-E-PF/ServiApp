
package com.egg.ServiApp.servicios;

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
    private trabajoRepositorio trabajoRepo;

    //Crear Trabajo
    @Transactional
    public void crearTrabajo(Usuario usuario, Proveedor proveedor) throws miException {
        validar(usuario, proveedor);
        Trabajo trabajo = new Trabajo();
        trabajo.setEstado(Estado.PENDIENTE);
        trabajo.setUsuario(usuario);
        trabajo.setProveedor(proveedor);

        trabajoRepo.save(trabajo);
    }

    //Modificar Trabajo
    @Transactional
    public void modificarTrabajo(String id, Estado estado) {
        Optional<Trabajo> trabajoOptional = trabajoRepo.findById(id);

        if (trabajoOptional.isPresent()) {
            Trabajo trabajo = trabajoOptional.get();
            trabajo.setEstado(estado);
            trabajoRepo.save(trabajo);
        }
    }

    //Listar Trabajo
    public List<Trabajo> listarTrabajos() {
        return trabajoRepo.findAll();
    }

    public Trabajo getTrabajo(String id) {
        return trabajoRepo.findById(id).orElse(null);
    }

    //Eliminar Trabajo
    @Transactional
    public void eliminarTrabajo(String id) {
        trabajoRepo.deleteById(id);
    }

    //Validar Trabajo
    private void validar(Usuario usuario, Proveedor proveedor) throws miException {
        if (usuario == null) {
            throw new miException("El usuario del trabajo no puede ser nulo");
        }
        if (proveedor == null) {
            throw new miException("Los proveedores del trabajo no pueden ser nulos");
        }
    }
}
