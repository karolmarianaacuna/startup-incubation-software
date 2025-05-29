package com.codewithteam.proyecto_integrador.Models.DAOS;

import com.codewithteam.proyecto_integrador.Entities.StartupEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StartupDAOS extends CrudRepository<StartupEntity, Long> {

    List<StartupEntity> findAll();

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

    //contra la cantidad de startups
    @Transactional
    @Query("SELECT COUNT(s) FROM StartupEntity s")
    long contarStartups();

    @Transactional
    @Query("SELECT s FROM StartupEntity s JOIN s.monitoriasStartup m WHERE m.usuarioMonitoria.idUsuario = :usuarioId")
    List<StartupEntity> findStartupsByUsuarioId(@Param("usuarioId") Long usuarioId);











//    // Buscar por sector (exacto)
//    @Transactional
//    @Query("SELECT s FROM StartupEntity s WHERE s.nombreStartup LIKE ")
//    List<StartupEntity> buscarPorNombre(String nombreStartup);



//
//    @Transactional
//    @Query("SELECT s FROM StartupEntity s WHERE LOWER(s.nombreStartup) LIKE LOWER(CONCAT('%', :nombreStartup, '%'))")
//    List<StartupEntity> buscarNombre(@Param("nombreStartup") String nombreStartup);







}

