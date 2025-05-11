package com.codewithteam.proyecto_integrador.Models.Service;

import com.codewithteam.proyecto_integrador.Entities.StartupEntity;
import com.codewithteam.proyecto_integrador.Models.DAOS.StartupDAOS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void deleteById(Long id) {
        startupDao.deleteById(id);
    }

    @Override
    public StartupEntity actualizarStartup(StartupEntity startup) {
        return startupDao.save(startup);
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
    public List<Object[]> countStartupsBySector() {
        return List.of();
    }
}
