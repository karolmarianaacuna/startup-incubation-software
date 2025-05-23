package com.codewithteam.proyecto_integrador.Controllers;

import com.codewithteam.proyecto_integrador.Entities.UsuarioEntity;
import com.codewithteam.proyecto_integrador.Models.DAOS.RolDAOS;
import com.codewithteam.proyecto_integrador.Models.DAOS.UsuarioDAOS;
import com.codewithteam.proyecto_integrador.Models.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/usuarios")
public class UsuariosController {

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
            usuario.setFechaCreacion(new Date()); // Establecer fecha de creación

            if (!imagenFile.isEmpty()) {
                // Generar un nombre único para el archivo
                String nombreArchivo = UUID.randomUUID().toString() + "_" + imagenFile.getOriginalFilename();
                Path ruta = Paths.get("src/main/resources/static/uploads/" + nombreArchivo);

                // Guardar el archivo en la carpeta "uploads"
                Files.write(ruta, imagenFile.getBytes());

                // Guardar solo el nombre del archivo en la entidad
                usuario.setImagenUsuario(nombreArchivo);
            }

            usuarioService.save(usuario);
            return "redirect:/usuarios/registro?success";

        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error al guardar el usuario o subir la imagen.");
            return "redirect:/usuarios/registro?error";
        }
    }

}