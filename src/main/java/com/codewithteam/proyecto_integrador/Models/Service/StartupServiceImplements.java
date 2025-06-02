package com.codewithteam.proyecto_integrador.Models.Service;

import com.codewithteam.proyecto_integrador.Entities.StartupEntity;
import com.codewithteam.proyecto_integrador.Models.DAOS.StartupDAOS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StartupServiceImplements implements StartupService {
    @Autowired
    private StartupDAOS startupDao;

    @Override
    public List<StartupEntity> findAll() {

        return (List<StartupEntity>) startupDao.findAll();
    }


    @Override
    public void save(StartupEntity startup) {

        startupDao.save(startup);
    }


    @Override
    public StartupEntity findById(Long id) {

        return startupDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        StartupEntity startup = startupDao.findById(id).orElse(null);
        if (startup != null) {
            startupDao.delete(startup); // Ya que cascade y orphanRemoval están bien configurados
            System.out.println("Startup eliminada con monitorías asociadas");
        } else {
            System.out.println("Startup no encontrada");
        }
    }

    @Override
    public void actualizaStartup(StartupEntity startup) {
        startupDao.save(startup);
    }


    @Override
    public StartupEntity viewDetail(Long id) {
        return startupDao.viewDetails(id);
    }

    @Override
    public List<StartupEntity> findByCategoria(String categoria) {
        return startupDao.buscarPorSector(categoria);
    }

    @Override
    public List<StartupEntity> findByName(String nombreStartup) {
        return startupDao.buscarPorNombre(nombreStartup);
    }

    @Override
    public long contarStartups() {
        return startupDao.contarStartups();
    }

    @Override
    public List<StartupEntity> findByUsuarioId(Long usuarioId) {
        return startupDao.findStartupsByUsuarioId(usuarioId);
    }


//    @Override
//    public List<StartupEntity> findByNombre(String nombreStartup) {
//        return findByNombre(nombreStartup);
//    }

//
//    @Override
//    public List<Object[]> countStartupsBySector() {
//        return List.of();
//    }
}
