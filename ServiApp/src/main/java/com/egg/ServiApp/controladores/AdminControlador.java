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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author catal
 */
@Controller

@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RequestMapping("/admin")

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

    @GetMapping("/userToProv/{id}")
    public String userToProv(@PathVariable String id, RedirectAttributes redirectAttributes) {
        
        usuarioServicio.usuarioCambioProveedor(usuarioServicio.UserById(id));
        usuarioServicio.eliminarUsuarioId(id);
        redirectAttributes.addFlashAttribute("exito", "Proceso éxitosoo");
        return "redirect:../usuarios";
    }
    
    @GetMapping("/provToUser/{id}")
    public String provToUser(@PathVariable String id, RedirectAttributes redirectAttributes) {
        
        usuarioServicio.proveedorCambioUsuario(usuarioServicio.ProviderById(id));
        redirectAttributes.addFlashAttribute("exito", "Proceso éxitosoo");
        return "redirect:../usuarios";
    }

    @GetMapping("/bajaUsuario/{id}")
    public String bajaUsuario(@PathVariable String id, RedirectAttributes redirectAttributes) {
        usuarioServicio.bajaUsuario(usuarioServicio.UserById(id));
        redirectAttributes.addFlashAttribute("exito", "Proceso éxitosoo");
        return "redirect:../usuarios";
    }

    @GetMapping("/modificarUsuario/{id}")
    public String modificarUsuario(@PathVariable String id, ModelMap model) {
        model.put("user", usuarioServicio.UserById(id));

        return "modUser.html";
    }
    
    @PostMapping("/modificarUsuario/{id}")
    public String modificarUsuario(MultipartFile archivo, @PathVariable String id, String nombre, Long telefono, ModelMap model){
        try {
            usuarioServicio.modificarUsuario(archivo, id, nombre, telefono);
            return "redirect:../usuarios";
        } catch (miException ex) {
            model.put("error", ex.getMessage());
            return "modUser.html";
        }
    }

    @GetMapping("/modificarProveedor/{id}")
    public String modificarProveedor(@PathVariable String id, ModelMap model) {
        model.put("provider", usuarioServicio.ProviderById(id));
        List<Especialidad> especialidades = especialidadServicio.listarEspecialidades();
        model.addAttribute("especialidades", especialidades);

        return "modProvider.html";
    }

    @PostMapping("/modificarProveedor/{id}")
    public String modificarProveedor(MultipartFile archivo, @PathVariable String id, String nombre, Long telefono, double costoHora, String idEsp, ModelMap model){
        try {
            usuarioServicio.modificarProveedor(archivo, id, nombre, telefono, costoHora, idEsp);
            return "redirect:../usuarios";
        } catch (miException ex) {
            model.put("error", ex.getMessage());
            return "modProvider.html";
        }
    }
    
    @GetMapping("/servicios")
    public String mostrarEspecialidad(ModelMap modelo) {
            List<Especialidad> especialidades = especialidadServicio.listarEspecialidades();
            modelo.addAttribute("especialidades", especialidades);
            return "servicios.html";
    }
    
    @PostMapping("/servicios")
    public String crearEspecialidad(@RequestParam String nombre, ModelMap model) {
        try {
            especialidadServicio.crearEspecialidad(nombre);
            return "redirect:../servicios";
        } catch (miException ex) {
            model.put("error", ex.getMessage());
            return "error.html";
        }
    }

    @PostMapping("/servicios/{id}")
    public String modificarEspecialidad(@PathVariable String id, @RequestParam String nombre, ModelMap model) {
        try {
            especialidadServicio.modificarEspecialidad(id, nombre);
            return "redirect:../servicios";
        } catch (miException ex) {
            model.put("error", ex.getMessage());
            model.put("user", usuarioServicio.UserById(id)); // Para mantener los datos en el formulario
            return "error.html";
        }
    }
  
}
