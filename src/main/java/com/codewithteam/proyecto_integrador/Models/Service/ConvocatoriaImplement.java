package com.codewithteam.proyecto_integrador.Models.Service;

import com.codewithteam.proyecto_integrador.Entities.ConvocatoriaEntity;
import com.codewithteam.proyecto_integrador.Models.DAOS.ConvocatoriaDAOS;
import com.codewithteam.proyecto_integrador.Models.DAOS.StartupDAOS;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConvocatoriaImplement implements ConvocatoriaService{

    @Autowired // Permite hacer una instacia
    private ConvocatoriaDAOS convocatoriaDAO;
    @Autowired
    private StartupDAOS startupDAO;

    @Override
    public List<ConvocatoriaEntity> findAll() {
        return  convocatoriaDAO.findAll();
    }

    @Override
    public void save(ConvocatoriaEntity convocatoria) {
        convocatoriaDAO.save(convocatoria);

    }


    //El optional Es una clase que representa un valor que puede o no existir. Se usa para evitar NullPointerException.
    @Override
    public Optional<ConvocatoriaEntity> findById(Long id) {
        return convocatoriaDAO.findById(id);
    }

    @Override
    public void deleteById(Long id) {
            convocatoriaDAO.deleteById(id);
    }

    @Override
    @Transactional
    public boolean deleteByIdIfNoStartups(Long id) {
        Optional<ConvocatoriaEntity> convocatoriaOptional = convocatoriaDAO.findById(id); //
        if (!convocatoriaOptional.isPresent()) {
            return false;
        }

        boolean hasStartups = startupDAO.existsByConvocatoria_IdConvocatoria(id);

        if (hasStartups) {
            System.out.println("No se puede eliminar la convocatoria ID " + id + ". Tiene startups asociadas.");
            return false;
        } else {

            convocatoriaDAO.deleteById(id);
            System.out.println("Convocatoria ID " + id + " eliminada exitosamente.");
            return true;
        }
    }

    @Override
    public ConvocatoriaEntity actualizarconvocatoria(ConvocatoriaEntity convocatoria) {
        return convocatoriaDAO.save(convocatoria);
    }

    @Override
    public void changeState(Long id) {

    }

    @Override
    public ConvocatoriaEntity viewDetails(Long id) {
        return convocatoriaDAO.verDetallesPorId(id);
    }

    @Override
    public List<ConvocatoriaEntity> filtrarPorEstado(String estado) {
        return convocatoriaDAO.categoriaActiva(estado);
    }

    @Override
    public long contarStartups() {
        return convocatoriaDAO.contarConvocatoriasActivas();
    }

    @Override
    public List<ConvocatoriaEntity> findByEstadoConvocatoriaIgnoreCase(String estadoConvocatoria) {
        return convocatoriaDAO.findByEstadoIgnoreCaseOrderByFechaDesc(estadoConvocatoria);
    }

}



