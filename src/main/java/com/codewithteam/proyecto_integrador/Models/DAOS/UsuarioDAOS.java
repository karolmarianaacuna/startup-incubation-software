package com.codewithteam.proyecto_integrador.Models.DAOS;

import com.codewithteam.proyecto_integrador.Entities.StartupEntity;
import com.codewithteam.proyecto_integrador.Entities.UsuarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuarioDAOS extends CrudRepository<UsuarioEntity, Long> {

    // Ver detalles de usuario por ID
    @Transactional
    @Query("SELECT u FROM UsuarioEntity u WHERE u.idUsuario = ?1")
    UsuarioEntity viewDetails(Long idStartup);



    // Buscar por correo
    @Transactional
    @Query("SELECT u FROM UsuarioEntity u WHERE u.correoUsuario = ?1")
    public UsuarioEntity findByCorreo(String correoUsuario);


    // Buscar por identificación
    @Transactional
    @Query("SELECT u FROM UsuarioEntity u WHERE u.identificacionUsuario = ?1")
    public UsuarioEntity findByIdentificacion(String identificacion);

// Buscar Rol por ID

    @Transactional
    @Query("SELECT u FROM UsuarioEntity u WHERE u.rol.idRol = ?1")
    public List<UsuarioEntity> findUsuariosByRolId(int idRol);


    //Buscar usuarios que no tienen monitorias

    @Transactional
    @Query("SELECT u FROM UsuarioEntity u WHERE SIZE(u.monitoriasUsuario) = 0")
    public List<UsuarioEntity> findUsuariosSinMonitorias();

    boolean existsByCorreoUsuario(String correoUsuario);
    boolean existsByIdentificacionUsuario(String identificacionUsuario);


}


