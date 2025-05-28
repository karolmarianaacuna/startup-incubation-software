package com.codewithteam.proyecto_integrador.Controllers;

import com.codewithteam.proyecto_integrador.Entities.UsuarioEntity;
import com.codewithteam.proyecto_integrador.Models.Service.EmailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class mentoriaController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/mentoria/solicitar")
    public String solicitarMentoria(HttpSession session, RedirectAttributes redirectAttributes) {
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            redirectAttributes.addFlashAttribute("error", "Debes iniciar sesión para solicitar una mentoría.");
            return "redirect:/login";
        }

        String asunto = "Nueva solicitud de mentoría";
        String contenido = "El usuario " + usuario.getNombreUsuario() +
                " (" + usuario.getCorreoUsuario() + ") ha solicitado una mentoría.";

        String correoAdmin = "mentoriasa72@gmail.com";

        try {
            emailService.enviarCorreo(correoAdmin, asunto, contenido);
            redirectAttributes.addFlashAttribute("success", "Mentoria solicitada correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo solicitar la mentoria.");
            e.printStackTrace();
        }

        return "redirect:/pruebaEmprendedor";
    }

}
