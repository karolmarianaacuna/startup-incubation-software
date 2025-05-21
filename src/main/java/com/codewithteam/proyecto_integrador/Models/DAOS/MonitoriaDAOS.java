package com.codewithteam.proyecto_integrador.Models.DAOS;

import com.codewithteam.proyecto_integrador.Entities.MonitoriaEntity;
import com.codewithteam.proyecto_integrador.Entities.UsuarioEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MonitoriaDAOS extends CrudRepository<MonitoriaEntity,Long> {


    @Query("SELECT DISTINCT u FROM MonitoriaEntity m " +
            "JOIN m.usuarioMonitoria u " +
            "WHERE m.startupMonitoria.idStartup = :idStartup")
    List<UsuarioEntity> encontrarUsuariosPorStartup(@Param("idStartup") Long idStartup);

}
