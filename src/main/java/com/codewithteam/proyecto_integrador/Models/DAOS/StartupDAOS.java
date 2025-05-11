package com.codewithteam.proyecto_integrador.Models.DAOS;

import com.codewithteam.proyecto_integrador.Entities.StartupEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StartupDAOS extends CrudRepository<StartupEntity, Long> {

    // Ver detalles de una startup por ID
    @Transactional
    @Query("SELECT s FROM StartupEntity s WHERE s.idStartup = ?1")
    StartupEntity viewDetails(Long idStartup);

    // Cambiar el nombre de una startup
    @Transactional
    @Modifying
    @Query("UPDATE StartupEntity s SET s.nombreStartup = ?2 WHERE s.idStartup = ?1")
    void cambiarNombreStartup(Long idStartup, String nuevoNombre);

    // Buscar por sector (exacto)
    @Transactional
    @Query("SELECT s FROM StartupEntity s WHERE s.sectorStartup = ?1")
    List<StartupEntity> buscarPorSector(String sectorStartup);


}

