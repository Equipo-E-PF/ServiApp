package com.egg.ServiApp.controladores;

import com.egg.ServiApp.entidades.Especialidad;
import com.egg.ServiApp.entidades.Proveedor;
import com.egg.ServiApp.entidades.Usuario;

import com.egg.ServiApp.servicios.usuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Ale y Choy
 */
@Controller
public class ProveedorControlador {

    @Autowired
    private usuarioServicio usuarioServicio;

    @GetMapping("/perfilProveedor")
    public String verPerfilProveedor(Model model) {
        // Supongamos que tienes un objeto 'usuario' que representa al usuario autenticado
        Usuario usuario = usuarioServicio.obtenerUsuarioAutenticado();

        // Obtener el proveedor asociado al usuario
        Proveedor proveedor = usuario.getProveedor();

        if (proveedor != null) {

            double puntuacion = proveedor.getPuntuacion();
            double costoHora = proveedor.getCostoHora();
            Especialidad especialidad = proveedor.getEspecialidad();

            model.addAttribute("proveedor", proveedor);
            model.addAttribute("puntuacion", puntuacion);
            model.addAttribute("costoHora", costoHora);
            model.addAttribute("especialidad", especialidad);

            return "perfilProveedor";
        } else {
            // El usuario no es un proveedor o no tiene un proveedor asociado
            // Realiza acciones apropiadas en este caso...
            return "sinProveedor";
        }
    }

}
