package com.codewithteam.proyecto_integrador.Controllers;

import com.codewithteam.proyecto_integrador.Entities.NoticiaEntity;
import com.codewithteam.proyecto_integrador.Models.Service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class noticiasController {

    @Autowired
    private NoticiaService noticiasService;

    @GetMapping("/blogNoticias")
    public String blogNoticias(Model model) {

        List<NoticiaEntity> lista = noticiasService.findAll();
        model.addAttribute("noticias", lista);

        //este es el nombre del html
        return "/noticias/blogNoticias";
    }

}
