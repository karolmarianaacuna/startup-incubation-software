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
        String correoAdmin = "mentoriasa72@gmail.com";
        String contenidoHtml = """
                <html lang="es">
                       <head>
                         <meta charset="UTF-8" />
                         <meta name="viewport" content="width=device-width, initial-scale=1" />
                         <style>
                           body {
                             margin: 0; padding: 0;
                             background-color: #f9f7f6;
                             font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                             color: #4a4033;
                           }
                           .email-container {
                             max-width: 600px;
                             margin: 40px auto;
                             background-color: #fff;
                             border-radius: 14px;
                             box-shadow: 0 6px 18px rgba(107, 79, 59, 0.15);
                             overflow: hidden;
                           }
                           .header {
                             background-color: #05A6A6;
                             padding: 20px;
                             text-align: center;
                           }
                           .header img {
                             max-height: 60px;
                           }
                           .content {
                             padding: 30px 40px;
                           }
                           h1 {
                             font-size: 22px;
                             font-weight: 800;
                             margin-bottom: 25px;
                             color: #EAF205;
                           }
                           p {
                             font-size: 16px;
                             line-height: 1.6;
                             margin: 14px 0;
                           }
                           .highlight {
                             color: #05A6A6;
                             font-weight: 600;
                             text-decoration: none;
                           }
                          
                           .footer {
                             font-size: 13px;
                             color: #a09688;
                             text-align: center;
                             padding: 15px 20px;
                             border-top: 1px solid #e0dcd7;
                             font-style: italic;
                           }
                         
                         </style>
                       </head>
                       <body>
                         <div class="email-container">
                           <div class="header">
                             <h1>emprendeUSTA</H1>
                           </div>
                           <div class="content">
                             <h1>📩 Nueva solicitud de mentoría</h1>
                             <p>Hola administrador,</p>
                             <p>El usuario <span class="highlight">%s</span> (<a href="mailto:%s" class="highlight">%s</a>) ha solicitado una mentoría.</p>
                             <p>Por favor, revisa esta solicitud desde el panel de administración de <strong>EmprendeUSTA</strong>.</p>
                             <p>Gracias por tu atención,</p>
                             <p><strong>Plataforma EmprendeUSTA</strong></p>
                           </div>
                           <div class="footer">
                             Este es un mensaje automático. Por favor, no respondas a este correo.
                           </div>
                         </div>
                       </body>
                       </html>
                
""".formatted(usuario.getNombreUsuario(), usuario.getCorreoUsuario(), usuario.getCorreoUsuario());



        try {
            emailService.enviarCorreoHtml(correoAdmin, asunto, contenidoHtml);
            redirectAttributes.addFlashAttribute("success", "Mentoría solicitada correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo solicitar la mentoría.");
            e.printStackTrace();
        }

        return "redirect:/pruebaEmprendedor";
    }

}
