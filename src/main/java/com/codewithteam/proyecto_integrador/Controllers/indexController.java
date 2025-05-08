package com.codewithteam.proyecto_integrador.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {


    @GetMapping("/blogNoticias")
    public String blogNoticias(){
    //este es el nombre del html
        return "/noticias/blogNoticias";
    }


}
