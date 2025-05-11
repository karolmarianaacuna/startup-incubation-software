package com.codewithteam.proyecto_integrador.Models.Service;

import com.codewithteam.proyecto_integrador.Entities.NoticiaEntity;

import java.util.List;

public interface NoticiaService {
    // Devuelve una lista con todas las noticias registradas en la base de datos.
    public List<NoticiaEntity> findAll();

    // Guarda una nueva noticia o actualiza una existente en la base de datos.
    public void save(NoticiaEntity noticia);

    // Busca una noticia por su ID y la devuelve.
    public NoticiaEntity findById(Long id);

    // Elimina la noticia que tenga el ID proporcionado.
    public void deleteById(Long id);

    // Actualiza una noticia existente con los nuevos datos proporcionados.
    public NoticiaEntity actualizarNoticia(NoticiaEntity noticia);

    // Devuelve los detalles de una noticia específica.
    public NoticiaEntity viewDetail(Long id);

}
