package com.egg.ServiApp.repositorio;

import com.egg.ServiApp.entidades.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ale y Choy
 */
@Repository 
public interface ImagenRepositorio extends JpaRepository<Imagen, String> {

}

    

