package com.egg.ServiApp.repositorio;

import com.egg.ServiApp.entidades.Trabajo;
import com.egg.ServiApp.enumeraciones.Estado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marco
 */

@Repository
public interface trabajoRepositorio extends JpaRepository<Trabajo, String> {

    @Query("SELECT a FROM Trabajo a WHERE a.calificacion = :calificacion")
    public Trabajo buscarPorCalificacion(@Param("calificacion") String calificacion);

    @Query("SELECT a FROM Trabajo a WHERE a.proveedor = :proveedor")
    public Trabajo buscarPorProveedor(@Param("proveedor") String proveedor);
    
    @Query("SELECT a FROM Trabajo a WHERE a.proveedor.id = :proveedorId")
    public List<Trabajo> listarPorProveedor(@Param("proveedorId") String proveedorId);

    @Query("SELECT a FROM Trabajo a WHERE a.estado = :estado")
    public Trabajo buscarPorEstado(@Param("estado") String estado);

    @Query("SELECT a FROM Trabajo a WHERE a.usuario = :usuario")
    public Trabajo buscarPorUsuario(@Param("usuario") String usaurio);

    @Query ("SELECT a FROM Trabajo a WHERE a.estado = :estado")
    public List<Trabajo> findByEstado(@Param("estado")Estado estado);
}
