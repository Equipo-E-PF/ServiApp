package com.egg.ServiApp.repositorio;

import com.egg.ServiApp.entidades.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marco
 */
@Repository
public interface calificacionRepositorio extends JpaRepository<Calificacion, String> {

    @Query("SELECT a FROM Calificacion a WHERE a.puntuacion = :puntuacion")
    public Calificacion buscarPorPuntuacion(@Param("puntuacion") String puntuacion);

}
