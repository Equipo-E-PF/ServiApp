/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.ServiApp.controladores;

import com.egg.ServiApp.entidades.Especialidad;
import com.egg.ServiApp.entidades.Proveedor;
import com.egg.ServiApp.entidades.Usuario;
import com.egg.ServiApp.enumeraciones.Rol;
import com.egg.ServiApp.servicios.especialidadServicio;
import com.egg.ServiApp.servicios.usuarioServicio;
import excepciones.miException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author catal
 */
@Controller
@RequestMapping("/perfil")
public class AdminControlador {

    @Autowired
    private usuarioServicio usuarioServicio;
    @Autowired
    private especialidadServicio especialidadServicio;

    @GetMapping("/usuarios")
    public String listarUsuarios(ModelMap model) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        List<Proveedor> proveedores = usuarioServicio.listarProveedores();
        List<Especialidad> especialidades = especialidadServicio.listarEspecialidades();
        model.addAttribute("especialidades", especialidades);    
        Rol[] rol = Rol.values();
        model.addAttribute("rol", rol);
        model.addAttribute("proveedores", proveedores);
        model.addAttribute("usuarios", usuarios);
        return "user_list.html";
    }

    @GetMapping("/cambiarRol/{id}")
    public String cambiarRol(@PathVariable String id, RedirectAttributes redirectAttributes, ModelMap model) {
        
        return "modifUser.html";
    }

    @PostMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable String id, RedirectAttributes redirectAttributes) {
        usuarioServicio.eliminarUsuarioId(id);
        redirectAttributes.addFlashAttribute("exito", "Proceso éxitosoo");
        return "redirect:../usuarios";
    }

    @GetMapping("/modificarUsuario/{id}")
    public String modificarUsuario(@PathVariable String id, ModelMap model) {
        model.put("user", usuarioServicio.UserById(id));

        return "modifUser.html";
    }
    
    @PostMapping("/modificarUsuario/{id}")
    public String modificarUsuario(@PathVariable String id, String nombre, Long telefono, ModelMap model){
        try {
            usuarioServicio.modificarUsuario(id, nombre, telefono);
            return "redirect:../usuarios";
        } catch (miException ex) {
            model.put("error", ex.getMessage());
            return "modifUser.html";
        }
    }

    @GetMapping("/modificarProveedor/{id}")
    public String modificarProveedor(@PathVariable String id, ModelMap model) {
        model.addAttribute("provider", usuarioServicio.ProviderById(id));

        return "modifUser.html";
    }

    @GetMapping("/proveedorAusuario/{id}")
    public String proveedorAusuario(@PathVariable String id, RedirectAttributes redirectAttributes) {
        //necesito aqui yo traigo el id de un proveedor, cambiar a usuario, dejar atributos especialidad y costo hora a null

        System.out.println(id);
        redirectAttributes.addFlashAttribute("exito", "Proceso éxitoso");
        return "redirect:../usuarios";
    }
}
