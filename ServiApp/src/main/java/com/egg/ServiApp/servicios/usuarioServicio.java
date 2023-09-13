/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.ServiApp.servicios;

import com.egg.ServiApp.entidades.Usuario;
import com.egg.ServiApp.enumeraciones.Rol;
import com.egg.ServiApp.repositorio.usuarioRepositorio;
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
public class usuarioServicio {
    
    @Autowired
    private usuarioRepositorio ur;
    
    @Transactional
    public void crearCliente (String nombre, String email, String password, int telefono) throws miException{
        
        validar(nombre, email, password, telefono);
        
        Usuario usuario = new Usuario();
        
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setTelefono(telefono);
        usuario.setBaja(false);
        usuario.setRol(Rol.CLIENTE);
        
        ur.save(usuario);
    }
    
    @Transactional
    public void modificarUsuario(String id, String nombre, String email, String password, int telefono) {

            Usuario usuario = ur.buscarPorId(id);
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setPassword(password);
            usuario.setTelefono(telefono);

            ur.save(usuario);

    }
    
    public List<Usuario> listarUsuarios(){
        
        return ur.findAll();
    }
    
    @Transactional
    public void eliminarUsuarioId(String id) throws miException{
        if (ur.findById(id).isPresent()) {
            ur.deleteById(id);
        }else{
            throw new miException("El id no existe");
        }
    }
    
     protected void validar(String nombre, String email, String password, int telefono) throws miException{
        
        if (nombre.isEmpty()||nombre==null) {
            throw new miException("El nombre no puede ser nulo");
        }
        if (email.isEmpty()||email==null) {
            throw new miException("El email no puede ser nulo");
        }
        if (password.isEmpty()||password==null||password.length()<=5) {
            throw new miException("El password no puede ser nulo y debe ser mayor que 5 digitos");
        }
        if (telefono < 6 || telefono > 20) {
            throw new miException("Ingrese un número válido");
        }
    }
}
    
