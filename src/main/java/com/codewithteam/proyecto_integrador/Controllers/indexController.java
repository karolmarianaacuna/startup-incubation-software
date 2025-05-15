package com.codewithteam.proyecto_integrador.Controllers;

import com.codewithteam.proyecto_integrador.Entities.NoticiaEntity;
import com.codewithteam.proyecto_integrador.Entities.StartupEntity;
import com.codewithteam.proyecto_integrador.Models.Service.NoticiaService;
import com.codewithteam.proyecto_integrador.Models.Service.StartupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class indexController {
    @Autowired
    private NoticiaService noticiasService;
    @Autowired
    private StartupService startupService;

    @GetMapping("/blogNoticias")
    public String blogNoticias(Model model) {
        List<NoticiaEntity> lista = noticiasService.findAll();
        model.addAttribute("noticias", lista);
        //este es el nombre del html
        return "/noticias/blogNoticias";
    }


    @GetMapping("/pruebaRegistrer")
    public String registro() {
        // Este es el nombre del html
        return "/registroInicio/registro";
    }

    @GetMapping("/pruebaLogin")
    public String Login() {
        // Este es el nombre del html
        return "/registroInicio/login";
    }

    @GetMapping("/pruebaFundadores")
    public String Fundador() {
        // Este es el nombre del html
        return "/fundadores/fundadores";
    }

    @GetMapping("/pruebaStartups")
    public String Startups(Model model) {

        List<StartupEntity> startups = startupService.findAll();
        model.addAttribute("startups", startups);
        return "/startups/startups";
    }
}


