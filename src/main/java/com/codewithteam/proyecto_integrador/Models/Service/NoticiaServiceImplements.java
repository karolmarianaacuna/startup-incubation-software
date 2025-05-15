package com.codewithteam.proyecto_integrador.Models.Service;

import com.codewithteam.proyecto_integrador.Entities.NoticiaEntity;
import com.codewithteam.proyecto_integrador.Models.DAOS.NoticiaDAOS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NoticiaServiceImplements implements NoticiaService{

    @Autowired
    private NoticiaDAOS noticiaDAOS;

    // Devuelve todas las noticias
    @Override
    public List<NoticiaEntity> findAll() {
        return (List<NoticiaEntity>) noticiaDAOS.findAll();
    }

    // Guarda o actualiza una noticia
    @Override
    public void save(NoticiaEntity noticia) {
        noticiaDAOS.save(noticia);
    }

    // Busca una noticia por su ID
    @Override
    public NoticiaEntity findById(Long id) {
        return noticiaDAOS.findById(id).orElse(null);
    }

    // Elimina una noticia por su ID
    @Override
    public void deleteById(Long id) {
        noticiaDAOS.deleteById(id);
    }

    // Actualiza una noticia existente
    @Override
    public NoticiaEntity actualizarNoticia(NoticiaEntity noticia) {
        return noticiaDAOS.save(noticia);
    }

    // Devuelve los detalles de una noticia (igual que findById en este caso)
    @Override
    public NoticiaEntity viewDetail(Long id) {
        return noticiaDAOS.findById(id).orElse(null);
    }

}

