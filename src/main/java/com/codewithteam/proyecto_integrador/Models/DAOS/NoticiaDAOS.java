package com.codewithteam.proyecto_integrador.Models.DAOS;

import com.codewithteam.proyecto_integrador.Entities.NoticiaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface NoticiaDAOS extends CrudRepository<NoticiaEntity, Long> {

    @Transactional
    @Query ("SELECT No FROM NoticiaEntity  No WHERE No.idNoticias=?1")
    public NoticiaEntity ViewDetail(Long idNoticias);
}
