package com.codewithteam.proyecto_integrador.Models.Service;

import com.codewithteam.proyecto_integrador.Entities.ConvocatoriaEntity;

import java.util.List;
import java.util.Optional;



public interface ConvocatoriaService {


    public List<ConvocatoriaEntity> findAll();
    public void save(ConvocatoriaEntity convocatoria);
    public Optional<ConvocatoriaEntity> findById(Long id);
    public void deleteById(Long id) ;
    public ConvocatoriaEntity actualizarconvocatoria(ConvocatoriaEntity convocatoria);
    public void changeState(Long id);
    public ConvocatoriaEntity viewDetails(Long id);
    public  List<ConvocatoriaEntity> filtrarPorEstado(String estado);
    public long contarStartups();
    List<ConvocatoriaEntity> findByEstadoConvocatoriaIgnoreCase(String estadoConvocatoria);
}



