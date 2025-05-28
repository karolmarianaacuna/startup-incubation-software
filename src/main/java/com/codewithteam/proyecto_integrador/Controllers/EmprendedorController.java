package com.codewithteam.proyecto_integrador.Controllers;

import com.codewithteam.proyecto_integrador.Entities.ConvocatoriaEntity;
import com.codewithteam.proyecto_integrador.Entities.MonitoriaEntity;
import com.codewithteam.proyecto_integrador.Entities.StartupEntity;
import com.codewithteam.proyecto_integrador.Entities.UsuarioEntity;
import com.codewithteam.proyecto_integrador.Models.Service.ConvocatoriaService;
import com.codewithteam.proyecto_integrador.Models.Service.StartupService;
import com.codewithteam.proyecto_integrador.Models.Service.UsuarioService;
import jakarta.servlet.http.HttpSession;
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
    private StartupService startupService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/pruebaEmprendedor")
    public String pruebaEmprendedor(Model model, HttpSession session) {
        // Agrega entidad vacía para el formulario de mentoría
        model.addAttribute("MonitoriaEntity", new MonitoriaEntity());
        // Lista de convocatorias activas
        List<ConvocatoriaEntity> convocatoriasActivas = convocatoriaService.findByEstadoConvocatoriaIgnoreCase("ACTIVA");
        model.addAttribute("convocatorias", convocatoriasActivas);
        // Obtener usuario logueado desde sesión
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuarioLogueado");
        // Si no hay usuario, evita errores (opcional)
        if (usuario == null) {
            model.addAttribute("startups", List.of()); // lista vacía
        } else {
            // Filtrar startups por correo del usuario
            List<StartupEntity> startupsUsuario = startupService.findAll().stream()
                    .filter(startup -> startup.getCorreoStartup().equalsIgnoreCase(usuario.getCorreoUsuario()))
                    .toList();
            model.addAttribute("startups", startupsUsuario);
        }
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
