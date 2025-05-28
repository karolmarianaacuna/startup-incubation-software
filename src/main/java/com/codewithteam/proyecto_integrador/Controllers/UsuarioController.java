package com.codewithteam.proyecto_integrador.Controllers;

import com.codewithteam.proyecto_integrador.Entities.UsuarioEntity;
import com.codewithteam.proyecto_integrador.Models.DAOS.RolDAOS;
import com.codewithteam.proyecto_integrador.Models.DAOS.UsuarioDAOS;
import com.codewithteam.proyecto_integrador.Models.Service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolDAOS rolDAOS;
    @Autowired
    private UsuarioDAOS usuarioDAOS;



    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("UsuarioEntity", new UsuarioEntity());
        model.addAttribute("roles", rolDAOS.findAll());
        return "registroInicio/registro";
    }


    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute("UsuarioEntity") UsuarioEntity usuario,
                                 @RequestParam("imagen") MultipartFile imagenFile,
                                 RedirectAttributes redirectAttributes) {
        try {
            // Verificar si ya existe un usuario con ese correo
            if (usuarioDAOS.existsByCorreoUsuario(usuario.getCorreoUsuario())) {
                redirectAttributes.addFlashAttribute("error", "El correo ya está registrado.");
                return "redirect:/usuario/registro?errorCorreo";
            }

            // Verificar si ya existe un usuario con esa identificación
            if (usuarioDAOS.existsByIdentificacionUsuario(usuario.getIdentificacionUsuario())) {
                redirectAttributes.addFlashAttribute("error", "La identificación ya está registrada.");
                return "redirect:/usuario/registro?errorIdentificacion";
            }

            String encriptada = passwordEncoder.encode(usuario.getContrasenaUsuario());
            usuario.setContrasenaUsuario(encriptada);




            usuario.setFechaCreacion(new Date());

            if (!imagenFile.isEmpty()) {
                String nombreArchivo = UUID.randomUUID().toString() + "_" + imagenFile.getOriginalFilename();
                Path ruta = Paths.get("src/main/resources/static/uploads/" + nombreArchivo);
                Files.write(ruta, imagenFile.getBytes());
                usuario.setImagenUsuario(nombreArchivo);
            }

            usuarioService.save(usuario);
            return "redirect:/usuario/registro?success";

        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error al guardar el usuario o subir la imagen.");
            return "redirect:/usuario/registro?error";
        }
    }


    @Autowired
    private PasswordEncoder passwordEncoder;






    @GetMapping("/pruebadashboardEmeprendedor")
    public String Emprendedor() {
        // Este es el nombre del html

        return "/dashboard/dashboardEmprendedor";
    }

    @PostMapping("/login")
    public String loginUsuario(@RequestParam("identificacion") String identificacion,
                               @RequestParam("contrasena") String contrasena,
                               RedirectAttributes redirectAttributes,
                               HttpSession session) {



        UsuarioEntity usuario = usuarioDAOS.findByIdentificacion(identificacion);



        if (usuario == null) {
            redirectAttributes.addFlashAttribute("error", "Usuario no encontrado.");
            return "redirect:/pruebaLogin"; // O la ruta de tu formulario
        }

        if (!passwordEncoder.matches(contrasena, usuario.getContrasenaUsuario())) {
            redirectAttributes.addFlashAttribute("error", "Contraseña incorrecta.");
            return "redirect:/pruebaLogin";
        }

        // Guardar usuario en sesión
        session.setAttribute("usuarioLogueado", usuario);

        // Redirigir según el rol (si lo deseas)
        String rol = usuario.getRol().getRol(); // Asumiendo que así accedes al rol

        if ("ROLE_EMPRENDEDOR".equalsIgnoreCase(rol)) {
            return "redirect:/pruebaEmprendedor";
        } else if ("ROLE_INVERSIONISTA".equalsIgnoreCase(rol)) {
            return "redirect:/panelStartups"; // ejemplo
        }

        return "redirect:/pruebaLogin"; // página por defecto
    }
}
