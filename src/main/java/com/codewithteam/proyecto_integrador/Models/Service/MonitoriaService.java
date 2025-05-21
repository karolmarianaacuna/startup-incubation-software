package com.codewithteam.proyecto_integrador.Models.Service;

import com.codewithteam.proyecto_integrador.Entities.UsuarioEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MonitoriaService {

    public List<UsuarioEntity> encontrarUsuariosPorStartup(Long idStartup);


}
