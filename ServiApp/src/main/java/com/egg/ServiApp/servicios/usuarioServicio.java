package com.egg.ServiApp.servicios;

import com.egg.ServiApp.entidades.Especialidad;
import com.egg.ServiApp.entidades.Imagen;
import com.egg.ServiApp.entidades.Proveedor;
import com.egg.ServiApp.entidades.Trabajo;
import com.egg.ServiApp.entidades.Usuario;
import com.egg.ServiApp.enumeraciones.Rol;
import com.egg.ServiApp.repositorio.especialidadRepositorio;
import com.egg.ServiApp.repositorio.trabajoRepositorio;
import com.egg.ServiApp.repositorio.usuarioRepositorio;
import excepciones.miException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Juanp
 */
@Service
public class usuarioServicio implements UserDetailsService {

    @Autowired
    private usuarioRepositorio ur;

    @Autowired
    private especialidadRepositorio er;

    @Autowired
    private especialidadServicio es;

    @Autowired
    private ImagenServicio is;

    @Autowired
    private trabajoRepositorio tr;

    @Transactional
    public void crearUsuario(String nombre, String email, String password, String password2, Long telefono) throws miException {

        validar(nombre, email, password, password2, telefono);

        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setTelefono(telefono);
        usuario.setBaja(false);
        usuario.setRol(Rol.USUARIO);

        ur.save(usuario);
    }

    @Transactional
    public void crearAdmin(String nombre, String email, String password, String password2, Long telefono) throws miException {

        validar(nombre, email, password, password2, telefono);

        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setTelefono(telefono);
        usuario.setBaja(false);
        usuario.setRol(Rol.ADMIN);

        ur.save(usuario);
    }

    @Transactional
    public void crearProveedor(String nombre, String email, String password, String password2, Long telefono, double costoHora, String especialidad) throws miException {

        validarP(nombre, email, password, password2, telefono, costoHora);

        Proveedor p = new Proveedor();
       
        p.setNombre(nombre);
        p.setEmail(email);
        p.setPassword(new BCryptPasswordEncoder().encode(password));
        p.setTelefono(telefono);
        p.setBaja(false);
        p.setRol(Rol.PROVEEDOR);
        p.setCostoHora(costoHora);
        p.setEspecialidad(er.buscarPorNombre(especialidad));

        ur.save(p);
    }

    @Transactional
    public void modificarUsuario(MultipartFile archivo, String id, String nombre, Long telefono) throws miException {

        Optional<Usuario> respuesta = ur.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuario.setNombre(nombre);
            usuario.setTelefono(telefono);
//            Imagen imagen = null;
//            String idImagen = null;
//
//            if (usuario.getImagen() != null) {
//                idImagen = usuario.getImagen().getId();
//                imagen = is.actualizar(archivo, idImagen);
//            } else {
//                imagen = is.guardar(archivo);
//
//            }
//
//            usuario.setImagen(imagen);
            ur.save(usuario);
        }
    }

    @Transactional
    public void modificarProveedor(MultipartFile archivo, String id, String nombre, Long telefono, double costoHora, String idEsp) throws miException {
        System.out.println(idEsp);
        Optional<Usuario> respuesta = ur.findById(id);
        if (respuesta.isPresent()) {
            Proveedor p = ur.proveedorPorId(id);
            p.setNombre(nombre);
            p.setTelefono(telefono);
            p.setCostoHora(costoHora);
            p.setEspecialidad(er.getById(idEsp));
//
//            Imagen imagen = null;
//            String idImagen = null;
//
//            if (p.getImagen() != null) {
//                idImagen = p.getImagen().getId();
//                imagen = is.actualizar(archivo, idImagen);
//            } else {
//                imagen = is.guardar(archivo);
//
//            }
//
//            p.setImagen(imagen);
            ur.save(p);

        }

    }
    
    @Transactional
    public void modificarContrasenia(String id, String oldPassword, String password1, String password2) throws miException{
        if (password1 == null || password1.isEmpty() || password1.length() <= 5) {
            throw new miException("El password no puede ser nulo y debe ser mayor que 5 digitos");
        }
        if (!password1.equals(password2)) {
            throw new miException("Las contraseñas deben coincidir");
        }
        Optional<Usuario> respuesta = ur.findById(id);
        Usuario user = new Usuario();
        if (respuesta.isPresent()) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(respuesta.get().getRol()==Rol.PROVEEDOR){
                user = ur.proveedorPorId(id);
            }else{
                user = ur.usuarioPorId(id);
            }
            String dbPassword = user.getPassword();
                if (passwordEncoder.matches(oldPassword, dbPassword)) {
                    user.setPassword(passwordEncoder.encode(password1));
                    ur.save(user);
                } else {
                    throw new miException("La contraseña ingresada no coincide con la anterior"); 
                }
        }
    }

    @Transactional
    public void calificarProveedor(String id, double calificacion) throws miException {
        Optional<Usuario> respuesta = ur.findById(id);
        if (respuesta.isPresent()) {
            Proveedor p = ur.proveedorPorId(id);
            if (p.getPuntuacion() == 0) {
                p.setPuntuacion(calificacion);
            } else {
                List<Trabajo> listaTrabajos = tr.listarPorProveedor(id);
                int trabajos = listaTrabajos.size();
                double nuevaCalificacion = Math.round((p.getPuntuacion() + calificacion) / trabajos);
                p.setPuntuacion(nuevaCalificacion);
            }

            ur.save(p);

        }
    }

    @Transactional

    public void usuarioCambioProveedor(Usuario usuario) {

        Proveedor p = usuario.convertirEnProveedor();
        p.setCostoHora(0);
        List<Especialidad> especialidades = es.listarEspecialidades();
        p.setEspecialidad(especialidades.get(0));
        ur.save(p);
    }

    @Transactional

    public void proveedorCambioUsuario(Proveedor proveedor) {

        proveedor.setRol(Rol.USUARIO);
        ur.save(proveedor);
    }

    @Transactional
    public void bajaUsuario(Usuario usuario) {

        usuario.setBaja(true);
        ur.save(usuario);
    }

    public Usuario UserById(String id) {
        return ur.getById(id);

    }

    public Proveedor ProviderById(String id) {
        return ur.proveedorPorId(id);

    }

    public void actualizarFoto(MultipartFile archivo, String id) throws miException {

        Optional<Usuario> respuesta = ur.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();

            Imagen imagen = null;
            String idImagen = null;

            if (usuario.getImagen() != null) {
                idImagen = usuario.getImagen().getId();
                imagen = is.actualizar(archivo, idImagen);
            } else {
                imagen = is.guardar(archivo);

            }

            usuario.setImagen(imagen);
            ur.save(usuario);

        }

    }

    public List<Usuario> listarUsuarios() {

        return ur.listaUsuarios(Rol.USUARIO);
    }

    public List<Proveedor> listarProveedores() {

        return ur.listaProveedores(Rol.PROVEEDOR);
    }

    @Transactional
    public void eliminarUsuarioId(String id) {
        ur.deleteById(id);
    }

    protected void validar(String nombre, String email, String password, String password2, Long telefono) throws miException {

        if (nombre == null || nombre.isEmpty()) {
            throw new miException("El nombre no puede ser nulo");
        }
        if (ur.buscarPorNombre(nombre) != null) {
            throw new miException("El nombre ya está siendo utilizado");
        }
        if (email == null || email.isEmpty()) {
            throw new miException("El email no puede ser nulo");
        }
        if (ur.buscarPorEmail(email) != null) {
            throw new miException("El Email ya está siendo utilizado");
        }
        if (password == null || password.isEmpty() || password.length() <= 5) {
            throw new miException("El password no puede ser nulo y debe ser mayor que 5 digitos");
        }
        if (!password.equals(password2)) {
            throw new miException("Las contraseñas deben coincidir");
        }
        if (telefono.toString().length() < 6 || telefono.toString().length() > 20) {
            throw new miException("El teléfono no es correcto");
        }
    }

    protected void validarP(String nombre, String email, String password, String password2, Long telefono, double costoHora) throws miException {

        if (nombre == null || nombre.isEmpty()) {
            throw new miException("El nombre no puede ser nulo");
        }
        if (ur.buscarPorNombre(nombre) != null) {
            throw new miException("El nombre ya está siendo utilizado");
        }
        if (email == null || email.isEmpty()) {
            throw new miException("El email no puede ser nulo");
        }
        if (password == null || password.isEmpty() || password.length() <= 5) {
            throw new miException("El password no puede ser nulo y debe ser mayor que 5 digitos");
        }
        if (ur.buscarPorEmail(email) != null) {
            throw new miException("El Email ya está siendo utilizado");
        }
        if (!password.equals(password2)) {
            throw new miException("Las contraseñas deben coincidir");
        }
        if (telefono.toString().length() < 6 || telefono.toString().length() > 20) {
            throw new miException("El teléfono no es correcto");
        }
        if (costoHora <= 0) {
            throw new miException("El costo de la hora no puede ser menor o igual a 0");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = ur.buscarPorEmail(email);

        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
            permisos.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usersession", usuario);

            return new User(usuario.getEmail(), usuario.getPassword(), permisos);
        } else {

            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }
    
    public HashSet<Proveedor> proveedorSearch(String search) {
        HashSet<Proveedor> resultados = ur.searchByEspecialidad(search);
        resultados.addAll(ur.searchByNombre(search));
        return resultados;
    }

    public Usuario getOne(String id) {
        return ur.getOne(id);
    }

}
