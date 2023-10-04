package com.egg.ServiApp.repositorio;

import com.egg.ServiApp.entidades.Trabajo;
import com.egg.ServiApp.entidades.Usuario;
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

    @Query("SELECT a FROM Trabajo a WHERE a.proveedor.id = :proveedorId AND a.estado = 'FINALIZADO'")
    public List<Trabajo> listarPorProveedor(@Param("proveedorId") String proveedorId);

    @Query("SELECT a FROM Trabajo a WHERE a.estado = :estado")
    public Trabajo buscarPorEstado(@Param("estado") String estado);

    @Query("SELECT a FROM Trabajo a WHERE a.usuario.id = :usuarioId AND a.estado = 'FINALIZADO'")
    public List<Trabajo> listarPorUsuario(@Param("usuarioId") String usaurioId);

    @Query("SELECT a FROM Trabajo a WHERE a.estado = :estado")
    public List<Trabajo> findByEstado(@Param("estado") Estado estado);

    @Query("SELECT DISTINCT t FROM Trabajo t WHERE t.proveedor.id = :proveedorId AND t.estado = :estado")
    public List<Trabajo> TrabajoPorProveedorEstado(@Param("proveedorId") String proveedorId, @Param("estado") Estado estado);

    @Query("SELECT DISTINCT t FROM Trabajo t WHERE t.usuario.id = :usuarioId AND t.estado = :estado")
    public List<Trabajo> TrabajoPorUsuarioEstado(@Param("usuarioId") String usuarioId, @Param("estado") Estado estado);
    
    @Query("SELECT DISTINCT t FROM Trabajo t WHERE t.proveedor.id = :proveedorId AND t.calificacion.puntuacion > 0 ")
    public List<Trabajo> TrabajoCalificadosProveedor (@Param("proveedorId") String proveedorId);
    
    @Query("SELECT DISTINCT t FROM Trabajo t WHERE t.usuario.id = :usuarioId AND t.calificacion.puntuacion > 0 ")
    public List<Trabajo> TrabajoCalificadosUsuario (@Param("usuarioId") String usuarioId);
    
    @Query("SELECT t FROM Trabajo t WHERE t.calificacion.puntuacion > 0 ")
    public List<Trabajo> TrabajosCalificados ();
}
