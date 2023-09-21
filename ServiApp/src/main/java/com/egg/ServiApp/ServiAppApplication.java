package com.egg.ServiApp;

import com.egg.ServiApp.servicios.usuarioServicio;
import excepciones.miException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiAppApplication {

    @Autowired
    private usuarioServicio us;

    public static void main(String[] args) throws miException {
        SpringApplication.run(ServiAppApplication.class, args);
    }

    @PostConstruct
    public void init() {
        try {
            us.crearUsuario("Pedrito", "email@email.com", "Password1", "Password1", 3143760609L);
            us.crearProveedor("Juan Angulo", "juanpa.angulo98@hotmail.com", "Passwrod2", "Passwrod2", 3143760609L, 15, null);
            us.crearProveedor("Marcos Romiti", "marcos.romiti@hotmail.com", "Passwrod3", "Passwrod3", 3143760609L, 15, null);
            us.crearProveedor("Alex Medina", "alex.medina@hotmail.com", "Passwrod4", "Passwrod4", 3143760609L, 15, null);
            us.crearProveedor("Facundo Torino", "facundo.torino@hotmail.com", "Passwrod5", "Passwrod5", 3143760609L, 15, null);
            us.crearProveedor("Alejandra Lopez", "alejandra.lopez@hotmail.com", "Passwrod6", "Passwrod6", 3143760609L, 15, null);
            us.crearProveedor("Mariana Torres", "mariana.torres@hotmail.com", "Passwrod7", "Passwrod7", 3143760609L, 15, null);
            us.crearProveedor("Rodrigo Pascual", "rodrigo.pascual@hotmail.com", "Passwrod8", "Passwrod8", 3143760609L, 15, null);
            us.crearProveedor("Jefferson Rodríguez", "jefferson.rodríguez@hotmail.com", "Passwrod9", "Passwrod9", 3143760609L, 15, null);
            
        } catch (miException e) {
        }

    }

}
