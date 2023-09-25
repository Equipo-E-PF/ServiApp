package com.egg.ServiApp.repositorio;

import com.egg.ServiApp.entidades.Proveedor;
import com.egg.ServiApp.entidades.Usuario;
import com.egg.ServiApp.enumeraciones.Rol;
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
public interface usuarioRepositorio extends JpaRepository<Usuario, String> {

    @Query("SELECT a FROM Usuario a WHERE a.email = :email")
    public Usuario buscarPorEmail(@Param("email") String email);

    @Query("SELECT a FROM Usuario a WHERE a.rol = :rol")
    public Usuario buscarPorRol(@Param("rol") String rol);

    @Query("SELECT u FROM Usuario u WHERE u.rol = :PROVEEDOR AND u.especialidad.nombre = :especialidad")
    public List<Usuario> buscarProveedoresPorServicio(@Param("especialidad") String especialidad);

    @Query("SELECT p FROM Proveedor p WHERE p.id= :id")
    public Proveedor proveedorPorId(@Param("id") String id);

    @Query("SELECT u FROM Usuario u WHERE u.rol= :USUARIO AND u.baja= 0")
    public List<Usuario> listaUsuarios(@Param("USUARIO") Rol rolUsuario);
    
    @Query("SELECT u FROM Usuario u WHERE u.rol= :PROVEEDOR AND u.baja= 0")
    public List<Proveedor> listaProveedores(@Param("PROVEEDOR") Rol rolProveedor);
}
