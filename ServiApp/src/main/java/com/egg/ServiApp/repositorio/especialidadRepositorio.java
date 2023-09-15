package com.egg.ServiApp.repositorio;

import com.egg.ServiApp.entidades.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marco
 */
@Repository
public interface especialidadRepositorio extends JpaRepository<Especialidad, String> {

    @Query("SELECT a FROM Especialidad a WHERE a.nombre = :nombre")
    public Especialidad buscarPorNombre(@Param("nombre") String nombre);

    @Query("SELECT a FROM Especialidad a WHERE a.id = :id")
    public Especialidad buscarPorId(@Param("id") String id);

}
