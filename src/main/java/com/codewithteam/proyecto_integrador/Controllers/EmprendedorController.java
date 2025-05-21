package com.codewithteam.proyecto_integrador.Controllers;

import com.codewithteam.proyecto_integrador.Entities.ConvocatoriaEntity;
import com.codewithteam.proyecto_integrador.Entities.MonitoriaEntity;
import com.codewithteam.proyecto_integrador.Entities.UsuarioEntity;
import com.codewithteam.proyecto_integrador.Models.Service.ConvocatoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EmprendedorController {

    @Autowired
    private ConvocatoriaService convocatoriaService;

    @GetMapping("/pruebaEmprendedor")
    public String pruebaEmprendedor(Model model) {
        model.addAttribute("MonitoriaEntity", new MonitoriaEntity());
        List<ConvocatoriaEntity> convocatorias = convocatoriaService.findAll();
        model.addAttribute("convocatorias", convocatorias);
        return "/emprendedor/VistaEmprendedor";
    }

}