package com.egg.ServiApp.controladores;

import com.egg.ServiApp.entidades.Especialidad;
import com.egg.ServiApp.entidades.Proveedor;
import com.egg.ServiApp.entidades.Usuario;
import com.egg.ServiApp.servicios.calificacionServicio;
import com.egg.ServiApp.servicios.especialidadServicio;
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
import org.springframework.web.servlet.ModelAndView;
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

    @PostMapping("/convertirEnProveedor")
    public ModelAndView convertirEnProveedor(@RequestParam("usuarioId") String usuarioId, @RequestParam("puntuacion") double puntuacion,
            @RequestParam("costoHora") double costoHora,
            @RequestParam("especialidadId") Long especialidadId
    ) {
        // Recupera el usuario por su ID 
        Usuario usuario = usuarioServicio.obtenerUsuarioPorId(usuarioId);

        // Recupera la especialidad por su ID 
        Especialidad especialidad = especialidadServicio.obtenerEspecialidadPorId(usuarioId);

        if (usuario != null && especialidad != null) {
            Proveedor proveedor = Usuario.convertirEnProveedor(usuario, puntuacion, costoHora, especialidad);

            // Guarda el nuevo proveedor en la base de datos 
            usuarioServicio.guardarProveedor(proveedor);

            // Opcionalmente, puedes marcar al usuario original como proveedor o realizar otras acciones necesarias
            return new ModelAndView("exito");
        } else {

            return new ModelAndView("error");
        }
    }

    @PostMapping("/crearTrabajo")
    public String crearTrabajo(@RequestParam("descripcion") String descripcion, RedirectAttributes redirectAttributes) {
        try {

            Usuario usuario = usuarioServicio.obtenerUsuarioAutenticado();

            // Obtener el proveedor asociado al usuario (esto dependerá de tu modelo de datos)
            Proveedor proveedor = usuario.getProveedor();

            trabajoServicio.crearTrabajo(usuario, proveedor);

            redirectAttributes.addFlashAttribute("exito", "Trabajo creado con éxito");
        } catch (miException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/usuario/dashboard"; // Ajusta la redirección según tu ruta real
    }

}
