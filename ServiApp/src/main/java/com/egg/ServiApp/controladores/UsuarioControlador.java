package com.egg.ServiApp.controladores;

import com.egg.ServiApp.entidades.Especialidad;
import com.egg.ServiApp.entidades.Trabajo;
import com.egg.ServiApp.entidades.Usuario;
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
 * @author Ale y Choy
 */
@Controller
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USUARIO', 'ROLE_PROVEEDOR')")
@RequestMapping("/usuario")

public class UsuarioControlador {

    @Autowired
    private trabajoServicio trabajoServicio;
    @Autowired
    private usuarioServicio usuarioServicio;
    @Autowired
    private calificacionServicio calificacionServicio;
  @Autowired
    private especialidadServicio especialidadServicio;
    @GetMapping("/perfil")

    public String cargarPerfil() {
        return "profileUser.html";
    }

    @GetMapping("/perfilOtro/{id}")
    public String perfilOtro(@PathVariable String id, ModelMap modelo) {

         
        System.out.println(usuarioServicio.ProviderById(id));
        List<Trabajo> trabajos = trabajoServicio.listarTrabajoPorProveedor(id);
        modelo.put("trabajos", trabajos);
        modelo.put("user", usuarioServicio.ProviderById(id));

        return "profileOtherUser.html";
    }

    @PostMapping("/actualizarFoto")
    public String registro(@RequestParam String id, MultipartFile archivo, ModelMap modelo) {
        try {
            usuarioServicio.actualizarFoto(archivo, id);
            modelo.put("exito", "Actualización correcta");
            return "profileUser.html";
        } catch (miException e) {
            modelo.put("error", e.getMessage());

        }
        return "profileUser.html";

    }
@GetMapping("/modificarUsuario/{id}")
    public String modificarUsuario(@PathVariable String id, ModelMap model) {
        model.put("user", usuarioServicio.UserById(id));

        return "modProfileUser.html";
    }
    
    @PostMapping("/modificarUsuario/{id}")
    public String modificarUsuario(MultipartFile archivo, @PathVariable String id, String nombre, Long telefono, ModelMap model){
        try {
            usuarioServicio.modificarUsuario(archivo, id, nombre, telefono);
            return "redirect:../perfil";
        } catch (miException ex) {
            model.put("error", ex.getMessage());
            return "modProfileUser.html";
        }
    }
    

    @GetMapping("/modificarProveedor/{id}")
    public String modificarProveedor(@PathVariable String id, ModelMap model) {
        model.put("provider", usuarioServicio.ProviderById(id));
        List<Especialidad> especialidades = especialidadServicio.listarEspecialidades();
        model.addAttribute("especialidades", especialidades);

        return "modProfileProvider.html";
    }

    @PostMapping("/modificarProveedor/{id}")
    public String modificarProveedor(MultipartFile archivo, @PathVariable String id, String nombre, Long telefono, double costoHora, String idEsp, ModelMap model){
        try {
            usuarioServicio.modificarProveedor(archivo, id, nombre, telefono, costoHora, idEsp);
            return "redirect:../perfil";
        } catch (miException ex) {
            model.put("error", ex.getMessage());
            return "modProfileProvider.html";
        }
    }
    // Cambiar el estado del trabajo a "Realizado"
    @GetMapping("/realizarTrabajo")
    public String realizarTrabajo(@RequestParam String trabajoId, RedirectAttributes redirectAttributes) {
        //trabajoServicio.modificarTrabajo(trabajoId, Estado.FINALIZADO);
        redirectAttributes.addFlashAttribute("exito", "Trabajo marcado como realizado");
        return "redirect:/";
    }

    // Cancelar un trabajo
    @GetMapping("/cancelarTrabajo")
    public String cancelarTrabajo(@RequestParam String trabajoId, RedirectAttributes redirectAttributes) {
        //trabajoServicio.modificarTrabajo(trabajoId, Estado.CANCELADO);
        redirectAttributes.addFlashAttribute("exito", "Trabajo cancelado con éxito");
        return "redirect:/";
    }

    // Crear una calificación para un trabajo con estrellas
    @PostMapping("/calificarTrabajo")
    public String calificarTrabajo(@RequestParam String trabajoId, @RequestParam String contenido, @RequestParam double puntuacion, RedirectAttributes redirectAttributes) {
        try {
            calificacionServicio.crearCalificacion(contenido, puntuacion);
            // Asociar la calificación al trabajo (debe implementarse en el servicio)
            trabajoServicio.asociarCalificacion(trabajoId, contenido);
            redirectAttributes.addFlashAttribute("exito", "Calificación creada con éxito");
        } catch (miException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/";
    }

}
