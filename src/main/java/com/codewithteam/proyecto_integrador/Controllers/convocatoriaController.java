package com.codewithteam.proyecto_integrador.Controllers;

import com.codewithteam.proyecto_integrador.Entities.ConvocatoriaEntity;
import com.codewithteam.proyecto_integrador.Models.Service.ConvocatoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class convocatoriaController {


    //siempre se le hace una inyeccion de el servicio al controlador

    @Autowired
    private ConvocatoriaService convocatoriaService;

    @GetMapping("/Convocatorias")
    public String listarConvocatoria(Model model) {

        List<ConvocatoriaEntity> lista = convocatoriaService.findAll();
        model.addAttribute("convocatorias", lista);

        return "/convocatoria/convocatoria";
    }





}
