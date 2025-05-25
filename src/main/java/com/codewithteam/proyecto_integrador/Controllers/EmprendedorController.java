package com.codewithteam.proyecto_integrador.Controllers;

import com.codewithteam.proyecto_integrador.Entities.ConvocatoriaEntity;
import com.codewithteam.proyecto_integrador.Entities.MonitoriaEntity;
import com.codewithteam.proyecto_integrador.Entities.UsuarioEntity;
import com.codewithteam.proyecto_integrador.Models.Service.ConvocatoriaService;
import com.codewithteam.proyecto_integrador.Models.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class EmprendedorController {

    @Autowired
    private ConvocatoriaService convocatoriaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/pruebaEmprendedor")
    public String pruebaEmprendedor(Model model) {

        // Se crea una nueva entidad de monitoría para el formulario
        model.addAttribute("MonitoriaEntity", new MonitoriaEntity());
        // Se obtienen únicamente las convocatorias activas
        List<ConvocatoriaEntity> convocatoriasActivas = convocatoriaService.findByEstadoConvocatoriaIgnoreCase("ACTIVA");
        // Se envían al modelo para mostrarlas en la vista
        model.addAttribute("convocatorias", convocatoriasActivas);
        return "/emprendedor/VistaEmprendedor";
    }

    //trae a cada startup con su usuario o los usuarios relacionados
    @GetMapping("/usuario/{id}")
    public String verUsuario(@PathVariable Long id, Model model) {

        UsuarioEntity usuario = usuarioService.findById(id);

        model.addAttribute("usuario", usuario);
        return "/detalles/detalleUsuario";
    }



}