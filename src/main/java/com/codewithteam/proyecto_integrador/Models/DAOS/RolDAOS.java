package com.codewithteam.proyecto_integrador.Models.DAOS;

import com.codewithteam.proyecto_integrador.Entities.RolEntity;
import org.springframework.data.repository.CrudRepository;

public interface RolDAOS extends CrudRepository<RolEntity,Integer> {

    // no necesitamos realizar esta ya que todo se hace desde a base de datos
}
