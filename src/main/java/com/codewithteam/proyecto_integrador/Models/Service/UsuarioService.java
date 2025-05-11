package com.codewithteam.proyecto_integrador.Models.Service;

import com.codewithteam.proyecto_integrador.Entities.UsuarioEntity;

import java.util.List;

public interface UsuarioService {

    // lista los usuarios
    public List<UsuarioEntity> findAll();

    //guarda o actualiza un usuario
    public void save(UsuarioEntity usuario);

    //busca un usuario por su id
    public UsuarioEntity findById(Long id);

    //elimina al usuario
    public void delete(Long id);

    //actualiza el usuario
    public UsuarioEntity actualizarUsuario(UsuarioEntity usuario);

    //Busca Usuario  correo

    public UsuarioEntity findByCorreo(String email);
}
