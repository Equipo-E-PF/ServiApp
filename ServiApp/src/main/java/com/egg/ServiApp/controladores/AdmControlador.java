package com.egg.ServiApp.controladores;

import com.egg.ServiApp.entidades.Especialidad;
import com.egg.ServiApp.entidades.Usuario;
import com.egg.ServiApp.enumeraciones.Rol;
import com.egg.ServiApp.servicios.especialidadServicio;
import com.egg.ServiApp.servicios.usuarioServicio;
import excepciones.miException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Ale y Choy
 */
@Controller

public class AdmControlador {

    @Autowired
    private usuarioServicio usuarioServicio;

    @Autowired
    private especialidadServicio especialidadServicio;

    // Mostrar lista de usuarios
    @GetMapping("/admin/usuarios")
    public String listarUsuarios(ModelMap model) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "listaUsuarios.html";
    }

    // Mostrar lista de especialidades
    @GetMapping("/admin/especialidades")
    public String listarEspecialidades(ModelMap model) {
        List<Especialidad> especialidades = especialidadServicio.listarEspecialidades();
        model.addAttribute("especialidades", especialidades);
        return "listaEspecialidades.html";
    }

    // Agregar una nueva especialidad
    @PostMapping("/admin/agregarEspecialidad")
    public String modificarEspecialidad(@RequestParam String nombre, RedirectAttributes redirectAttributes) {
        try {
            especialidadServicio.crearEspecialidad(nombre);
            redirectAttributes.addFlashAttribute("exito", "Especialidad creada con éxito");
        } catch (miException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/especialidades";
    }

    // Eliminar una especialidad
    @GetMapping("/admin/eliminarEspecialidad")
    public String eliminarEspecialidad(@RequestParam String id, RedirectAttributes redirectAttributes) {
        especialidadServicio.eliminarEspecialidadId(id);
        redirectAttributes.addFlashAttribute("exito", "Especialidad eliminada con éxito");
        return "redirect:/admin/especialidades";
    }

    // Eliminar un usuario
    @GetMapping("/admin/eliminarUsuario")
    public String eliminarUsuario(@RequestParam String id, RedirectAttributes redirectAttributes) {
        usuarioServicio.eliminarUsuarioId(id);
        redirectAttributes.addFlashAttribute("exito", "Usuario eliminado con éxito");
        return "redirect:/admin/usuarios";
    }
}
