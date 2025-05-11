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

    public StartupEntity actualizarStartup(StartupEntity startup);
// Actualiza una startup existente con los nuevos datos proporcionados.

    public StartupEntity viewDetail(Long id);
// Similar a findById, devuelve los detalles de una startup específica.

    public List<StartupEntity> findByCategoria(String categoria);
// Devuelve una lista de startups que pertenezcan a una categoría específica.

    List<Object[]> countStartupsBySector();
// Cuenta cuántas startups hay por cada sector.

}
