/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.ServiApp.controladores;

import com.egg.ServiApp.entidades.Especialidad;
import com.egg.ServiApp.servicios.especialidadServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author catal
 */
@Controller
@RequestMapping("/")
public class PortalControlador {
@Autowired
    private especialidadServicio especialidadServicio;
    @GetMapping("/")
    public String index(ModelMap model) {
        List<Especialidad> especialidades=especialidadServicio.listarEspecialidades();
        model.addAttribute("especialidades", especialidades);
        return "regUser.html";
    }
}
