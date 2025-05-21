package com.codewithteam.proyecto_integrador.Models.Service;

import com.codewithteam.proyecto_integrador.Entities.UsuarioEntity;
import com.codewithteam.proyecto_integrador.Models.DAOS.ConvocatoriaDAOS;
import com.codewithteam.proyecto_integrador.Models.DAOS.MonitoriaDAOS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitoriaImplement implements MonitoriaService {

    @Autowired // Permite hacer una instacia
    private MonitoriaDAOS monitoriaDAOS;

    @Override
    public List<UsuarioEntity> encontrarUsuariosPorStartup(Long idStartup) {
        return monitoriaDAOS.encontrarUsuariosPorStartup(idStartup);
    }
}
