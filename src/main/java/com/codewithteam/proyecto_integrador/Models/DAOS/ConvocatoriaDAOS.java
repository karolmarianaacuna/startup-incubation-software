package com.codewithteam.proyecto_integrador.Models.DAOS;

import com.codewithteam.proyecto_integrador.Entities.ConvocatoriaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ConvocatoriaDAOS extends CrudRepository<ConvocatoriaEntity,Long> {

    // esto es lo mismo que hacer la consulta
     List<ConvocatoriaEntity> findAll();

     @Transactional
    @Query("SELECT CO FROM ConvocatoriaEntity  CO WHERE CO.idConvocatoria=?1")
     public ConvocatoriaEntity verDetallesPorId(long id);

    @Transactional
    @Query("SELECT c FROM ConvocatoriaEntity c WHERE c.estadoConvocatoria = :estado")
    List<ConvocatoriaEntity> categoriaActiva(@Param("estado") String estado);

    @Query("SELECT COUNT(c) FROM ConvocatoriaEntity c WHERE c.estadoConvocatoria = 'activa'")
    long contarConvocatoriasActivas();

    @Transactional
    @Query("SELECT c FROM ConvocatoriaEntity c WHERE LOWER(c.estadoConvocatoria) = LOWER(:estado) ORDER BY c.fechaCreacion DESC")
    List<ConvocatoriaEntity> findByEstadoIgnoreCaseOrderByFechaDesc(@Param("estado") String estado);


}
