package com.egg.ServiApp;

import com.egg.ServiApp.enumeraciones.Estado;
import com.egg.ServiApp.servicios.calificacionServicio;
import com.egg.ServiApp.servicios.especialidadServicio;
import com.egg.ServiApp.servicios.trabajoServicio;
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
    
    @Autowired
    private especialidadServicio es;
    
    @Autowired
    private calificacionServicio cs;
    
    @Autowired
    private trabajoServicio ts;

    public static void main(String[] args) throws miException {
        SpringApplication.run(ServiAppApplication.class, args);
    }


//    @PostConstruct
//    public void init() {
//        try {
//            
//           //Creación de especialidades
//
//            
//            es.crearEspecialidad("Sin especialidad");
//            es.crearEspecialidad("Mecánico");
//            es.crearEspecialidad("Plomero");
//            es.crearEspecialidad("Gasista");
//            es.crearEspecialidad("Electricista");
//            es.crearEspecialidad("Pintor");
//            es.crearEspecialidad("Jardinero");
//            
//            // Creación de administrador
//            
//            us.crearAdmin("Admin Supremo", "admin@admin.com", "111111", "111111", 3143760609L);
//            
//            // Creación de usuarios
//            
//            us.crearUsuario("Pedro Velandia", "usuario1@email.com", "111111", "111111", 3143785909L);
//            us.crearUsuario("Luis Romero", "usuario2@email.com", "Usuario2", "Usuario2", 315741698L);
//            us.crearUsuario("Juan Calamardo", "usuario3@email.com", "Usuario3", "Usuario3", 3215489652L);
//            us.crearUsuario("German Garcia", "usuario4@email.com", "Usuario4", "Usuario4", 3004521485L);
//            us.crearUsuario("Laura Lopez", "usuario5@email.com", "Usuario5", "Usuario5", 3065203698L);
//            
//            // Creación proveedores
//            
//            us.crearProveedor("Juan Angulo", "juan.angulo@hotmail.com", "111111", "111111", 3143760609L, 15, "Electricista");
//            us.crearProveedor("Marcos Romiti", "marcos.romiti@hotmail.com", "111111", "111111", 3259875169L, 20, "Mecánico");
//            us.crearProveedor("Alex Medina", "alex.medina@hotmail.com", "Proveedor3", "Proveedor3", 3206548765L, 10, "Carpintero");
//            us.crearProveedor("Facundo Torino", "facundo.torino@hotmail.com", "Proveedor4", "Proveedor4", 3236549632L, 50, "Plomero");
//            us.crearProveedor("Alejandra Lopez", "alejandra.lopez@hotmail.com", "Proveedor5", "Proveedor5", 3159548724L, 8, "Jardinero");
//            us.crearProveedor("Mariana Torres", "mariana.torres@hotmail.com", "Proveedor6", "Proveedor6", 3226518743L, 30, "Gasista");
//            us.crearProveedor("Rodrigo Pascual", "rodrigo.pascual@hotmail.com", "Proveedor7", "Proveedor7", 3174568520L, 27, "Mecánico");
//            us.crearProveedor("Jefferson Rodríguez", "jefferson.rodríguez@hotmail.com", "Proveedor8", "Proveedor8", 3205418741L, 6, "Pintor");
//          
//           // Creación de trabajo
//          
//           // ts.crearTrabajo(us.listarUsuarios().get(0), us.listarProveedores().get(0));
//          
//            // Terminar trabajo
//            
//           // ts.terminarTrabajo(ts.listarTrabajos().get(0).getId(), Estado.FINALIZADO, "Ha cumplido con mis expectativas", 5);
//            
//            
//            
//        } catch (miException e) {
//            e.getStackTrace();
//        }
//    }

}
