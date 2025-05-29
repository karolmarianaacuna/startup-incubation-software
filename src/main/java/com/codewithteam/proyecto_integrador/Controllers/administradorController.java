package com.codewithteam.proyecto_integrador.Controllers;

import com.codewithteam.proyecto_integrador.Entities.ConvocatoriaEntity;
import com.codewithteam.proyecto_integrador.Entities.StartupEntity;
import com.codewithteam.proyecto_integrador.Entities.UsuarioEntity;
import com.codewithteam.proyecto_integrador.Models.Service.ConvocatoriaService;
import com.codewithteam.proyecto_integrador.Models.Service.StartupService;
import com.codewithteam.proyecto_integrador.Models.Service.UsuarioService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class administradorController {

    private final ConvocatoriaService convocatoriaService;
    private final UsuarioService usuarioService;
    private final StartupService startupService;

    public administradorController(ConvocatoriaService convocatoriaService, UsuarioService usuarioService, StartupService startupService) {
        this.convocatoriaService = convocatoriaService;
        this.usuarioService = usuarioService;
        this.startupService =  startupService;
    }



    @GetMapping("/pruebaAdministrador")
    public String administrador(Model model) {
        List<ConvocatoriaEntity> lista = convocatoriaService.findAll();
        model.addAttribute("convocatorias",lista );
        return "/administrador/administradorVista";

    }

    // Redirige a vista para gestionar usuarios
    @GetMapping("/gestionarUsuarios")
    public String gestionarUsuarios(Model model) {
        List<UsuarioEntity> listaUsu = usuarioService.findAll();
        model.addAttribute("usuario",listaUsu );
        return "/administrador/gestionarUsuarios";
    }

    // Redirige a vista para asignar mentorias
    @GetMapping("/asignarMentorias")
    public String asignarMentorias(Model model) {
        List<StartupEntity> listaStar = startupService.findAll();
        model.addAttribute("proyecto",listaStar );
        return "/administrador/asignarMentorias";
    }
}
