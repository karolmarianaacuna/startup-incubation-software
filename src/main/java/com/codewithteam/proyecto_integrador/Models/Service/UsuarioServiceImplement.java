package com.codewithteam.proyecto_integrador.Models.Service;

import com.codewithteam.proyecto_integrador.Entities.UsuarioEntity;
import com.codewithteam.proyecto_integrador.Models.DAOS.UsuarioDAOS;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UsuarioServiceImplement implements UsuarioService{


    private final UsuarioDAOS usuarioDAOS;

    public UsuarioServiceImplement(UsuarioDAOS usuarioDAOS) {
        this.usuarioDAOS = usuarioDAOS;
    }

    @Override
    public List<UsuarioEntity> findAll() {
        return (List<UsuarioEntity>) usuarioDAOS.findAll();
    }

    @Override
    public void save(UsuarioEntity usuario) {
        usuarioDAOS.save(usuario);
    }

    @Override
    public UsuarioEntity findById(Long id) {
        return usuarioDAOS.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        usuarioDAOS.deleteById(id);
    }

    @Override
    public UsuarioEntity actualizarUsuario(UsuarioEntity usuario) {
        return usuarioDAOS.save(usuario);
    }

    @Override
    public UsuarioEntity findByCorreo(String email) {
        return usuarioDAOS.findByCorreo(email);
    }
}

