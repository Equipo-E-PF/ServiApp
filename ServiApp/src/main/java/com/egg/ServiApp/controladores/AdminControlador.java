/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.ServiApp.controladores;

import com.egg.ServiApp.entidades.Especialidad;
import com.egg.ServiApp.entidades.Proveedor;
import com.egg.ServiApp.entidades.Trabajo;
import com.egg.ServiApp.entidades.Usuario;
import com.egg.ServiApp.enumeraciones.Rol;
import com.egg.ServiApp.repositorio.usuarioRepositorio;
import com.egg.ServiApp.servicios.calificacionServicio;
import com.egg.ServiApp.servicios.especialidadServicio;
import com.egg.ServiApp.servicios.trabajoServicio;
import com.egg.ServiApp.servicios.usuarioServicio;
import excepciones.miException;
import java.util.List;
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
    @Autowired
    private trabajoServicio ts;
    @Autowired
    private calificacionServicio cs;
    @Autowired
    private usuarioRepositorio ur;

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
    public String modificarUsuario(MultipartFile archivo, @PathVariable String id, String nombre, Long telefono, ModelMap model) {
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
    public String modificarProveedor(MultipartFile archivo, @PathVariable String id, String nombre, Long telefono, double costoHora, String idEsp, ModelMap model) {
        try {
            String descripcion = ur.proveedorPorId(id).getDescripcion();
            usuarioServicio.modificarProveedor(archivo, id, nombre, telefono, costoHora, descripcion, idEsp);
            return "redirect:../usuarios";
        } catch (miException ex) {
            model.put("error", ex.getMessage());
            return "modProvider.html";
        }
    }

    @GetMapping("/listaEspecialidades")
    public String verListaEspecialidades(ModelMap model) {
        List<Especialidad> especialidades = especialidadServicio.listarEspecialidades();
        model.addAttribute("especialidades", especialidades);
        return "especialidades.html"; // Reemplaza esto con tu página de edición real
    }

    @PostMapping("/editarEspecialidad/{id}")
    public String actualizarEspecialidad(@PathVariable String id, @RequestParam String nombre) throws miException {
        especialidadServicio.modificarEspecialidad(id, nombre);
        return "redirect:../listaEspecialidades"; 
    }

    @GetMapping("/nuevaEspecialidad")
    public String nuevaEspecialidad() {
        return "especialidadForm.html";
    }

    @PostMapping("/crearEspecialidad")
    public String crearEspecialidad(@RequestParam String nombre, ModelMap model) {
        try {
            especialidadServicio.crearEspecialidad(nombre);
            return "redirect:../admin/listaEspecialidades";
        } catch (miException ex) {
            model.put("error", ex.getMessage());
            return "error.html";
        }
    }

    @PostMapping("/eliminarEspecialidad/{id}")
    public String eliminarEspecialidad(@PathVariable String id) {
        especialidadServicio.eliminarEspecialidadId(id);
        return "redirect:../listaEspecialidades";
    }

    @GetMapping("/calificaciones")
    public String comentarios(ModelMap model) {
        List<Especialidad> especialidades = especialidadServicio.listarEspecialidades();
        model.addAttribute("especialidades", especialidades);

        List<Trabajo> trabajoCalificado = ts.listarTrabajoCalificado();
        model.addAttribute("trabajoCalificado", trabajoCalificado);

        return "contratacionesAdmin.html";
    }
    
    @PostMapping("/censurar/{id}")
    public String censurar(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            cs.censurarCalificacion(id);
            redirectAttributes.addFlashAttribute("exito", "Trabajo rechazado con éxito");
        } catch (miException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:../calificaciones";
    }

}
