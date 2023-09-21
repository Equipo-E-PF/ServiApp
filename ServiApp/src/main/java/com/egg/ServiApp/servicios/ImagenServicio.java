package com.egg.ServiApp.servicios;

import com.egg.ServiApp.entidades.Imagen;
import com.egg.ServiApp.repositorio.ImagenRepositorio;
import excepciones.miException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Ale y Choy
 */

@Service
public class ImagenServicio {
    @Autowired
     private ImagenRepositorio imagenRepositorio;
    
    public Imagen guardar(MultipartFile archivo) throws miException{
        if (archivo != null){
            try {
                Imagen imagen = new Imagen();
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                return imagenRepositorio.save(imagen);
                
            }catch (Exception e){
               System.err.println(e.getMessage()); 
            }
        }
        return null;
    }
    
    public Imagen actualizar (MultipartFile archivo, String idImagen) throws miException {
        if (archivo != null){
            try {
                Imagen imagen = new Imagen();
                if(idImagen != null){
                    Optional<Imagen> repuesta = imagenRepositorio.findById(idImagen);
                    
                    if(repuesta.isPresent()){
                        imagen = repuesta.get();
                    }
                }
                
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                return imagenRepositorio.save(imagen);
                
            }catch (Exception e){
               System.err.println(e.getMessage()); 
            }
        }
        return null;
    }

}
