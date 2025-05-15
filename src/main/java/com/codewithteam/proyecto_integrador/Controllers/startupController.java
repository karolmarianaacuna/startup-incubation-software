package com.codewithteam.proyecto_integrador.Controllers;


import com.codewithteam.proyecto_integrador.Entities.StartupEntity;
import com.codewithteam.proyecto_integrador.Models.Service.StartupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class startupController {

    @Autowired
    private StartupService startupService;

    @GetMapping("/pruebaStartups")
    public String Startups(@RequestParam(required = false) String categoria, Model model) {


        List<StartupEntity> startups;


        if (categoria != null && !categoria.isEmpty()) {
            startups = startupService.findByCategoria(categoria);

            model.addAttribute("categoriaSeleccionada", categoria);
        } else {
            startups = startupService.findAll();
        }

        model.addAttribute("startups", startups);

        System.out.println("Categoría seleccionada: " + categoria);

        return "/startups/startups";
    }








}
