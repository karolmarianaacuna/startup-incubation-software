package com.codewithteam.proyecto_integrador.Models.Service;

import com.codewithteam.proyecto_integrador.Entities.StartupEntity;

import java.util.List;

public interface StartupService {

    public List<StartupEntity> findAll();
    // Devuelve una lista con todas las startups registradas en la base de datos.

    public void save(StartupEntity startup);
    // Guarda una nueva startup o actualiza una existente en la base de datos.

    public StartupEntity findById(Long id);
    // Busca una startup por su ID y la devuelve.

    public void deleteById(Long id);
    // Elimina la startup que tenga el ID proporcionado.

    public void actualizaStartup(StartupEntity startup);
    // Guarda una nueva startup o actualiza una existente en la base de datos.

    public StartupEntity viewDetail(Long id);
    // Similar a findById, devuelve los detalles de una startup específica.

    public List<StartupEntity> findByCategoria(String categoria);
    // Devuelve una lista de startups que pertenezcan a una categoría específica.

    public long contarStartups();

    List<StartupEntity> findByUsuarioId(Long usuarioId);
    // Devuelve una lista de startups asociadas a un usuario específico por su ID.













}
