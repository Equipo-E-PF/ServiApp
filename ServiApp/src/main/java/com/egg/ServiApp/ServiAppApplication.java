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
            us.crearUsuario("Juan Angulo", "email@email.com", "Password1", 3143760609L);
            us.crearProveedor("Juan Angulo", "juanpa.angulo98@hotmail.com", "Passwrod2", 3143760609L, 15, null);
        } catch (miException e) {
        }
    }

}
