package com.codewithteam.proyecto_integrador.Models.DAOS;

import com.codewithteam.proyecto_integrador.Entities.ConvocatoriaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConvocatoriaDAOS extends CrudRepository<ConvocatoriaEntity,Long> {

    //Buscar convocatorias portítulo
    @Transactional
    @Query()
    List<ConvocatoriaEntity> findByTitulo(String titulo);





}
