package com.egg.ServiApp.controladores;

import com.egg.ServiApp.servicios.calificacionServicio;
import com.egg.ServiApp.servicios.trabajoServicio;
import com.egg.ServiApp.servicios.usuarioServicio;
import excepciones.miException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/perfil")

    public String cargarPerfil() {
        return "profileUser.html";
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
