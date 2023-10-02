package com.egg.ServiApp.controladores;

import com.egg.ServiApp.entidades.Trabajo;
import com.egg.ServiApp.servicios.trabajoServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Ale y Choy
 */
@Controller
public class TrabajoControlador {

    @Autowired
    private trabajoServicio trabajoServicio;

    @PostMapping("/aceptarTrabajo/{id}")
    public String aceptarTrabajo(@PathVariable String id) {
        trabajoServicio.aceptarTrabajo(id);
        return "redirect:/listarTrabajos";
    }

    @PostMapping("/rechazarTrabajo/{id}")
    public String rechazarTrabajo(@PathVariable String id) {
        trabajoServicio.rechazarTrabajo(id);
        return "redirect:/listarTrabajos";
    }

    @GetMapping("/listarTrabajosPendientes")
    public String listarTrabajosPendientes(Model model) {
        List<Trabajo> trabajosPendientes = trabajoServicio.listarTrabajosPendientes();
        model.addAttribute("trabajos", trabajosPendientes);
        return "listaTrabajo.html";
    }

    @GetMapping("/listarTrabajosAceptados")
    public String listarTrabajosAceptados(Model model) {
        List<Trabajo> trabajosAceptados = trabajoServicio.listarTrabajosAceptados();
        model.addAttribute("trabajos", trabajosAceptados);
        return "listTrabajo";
    }

    @GetMapping("/listarTrabajosCompletados")
    public String listarTrabajosCompletados(Model model) {
        List<Trabajo> trabajosCompletados = trabajoServicio.listarTrabajosCompletados();
        model.addAttribute("trabajos", trabajosCompletados);
        return "listarTrabajos";
    }
}
