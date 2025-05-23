package com.codewithteam.proyecto_integrador.Controllers;

import com.codewithteam.proyecto_integrador.Entities.ConvocatoriaEntity;
import com.codewithteam.proyecto_integrador.Models.Service.ConvocatoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class administradorController {

    private final ConvocatoriaService convocatoriaService;

    public administradorController(ConvocatoriaService convocatoriaService) {
        this.convocatoriaService = convocatoriaService;
    }

    @GetMapping("/pruebaAdministrador")
    public String administrador(Model model) {
        List<ConvocatoriaEntity> lista = convocatoriaService.findAll();
        model.addAttribute("convocatorias",lista );
        return "/administrador/administradorVista";

    }
}
