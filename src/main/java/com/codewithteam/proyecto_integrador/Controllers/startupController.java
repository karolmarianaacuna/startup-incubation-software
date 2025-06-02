package com.codewithteam.proyecto_integrador.Controllers;


import com.codewithteam.proyecto_integrador.Entities.StartupEntity;
import com.codewithteam.proyecto_integrador.Entities.UsuarioEntity;
import com.codewithteam.proyecto_integrador.Models.Service.MonitoriaService;
import com.codewithteam.proyecto_integrador.Models.Service.StartupService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class startupController {

    @Autowired
    private StartupService startupService;
    @Autowired
    private MonitoriaService monitoriaService;



    @GetMapping("/panelStartups")
    public String inicioStartup(@RequestParam(required = false) String categoria, Model model) {
        List<StartupEntity> startupsporCategoria;



        if (categoria != null && !categoria.isEmpty()) {
            startupsporCategoria = startupService.findByCategoria(categoria);
        } else {
            startupsporCategoria = startupService.findAll();
        }

        model.addAttribute("startupsporCategoria", startupsporCategoria);
        model.addAttribute("categoriaSeleccionada", categoria); // Esto es importante para mantener el <select>
        return "/startups/startups";
    }


    //trae a cada startup con su usuario o los usuarios relacionados
    @GetMapping("/startups/{id}")
    public String verStartup(@PathVariable Long id, Model model) {

        StartupEntity startup = startupService.viewDetail(id);
        List<UsuarioEntity> usuariosRelacionados = monitoriaService.encontrarUsuariosPorStartup(id);


        model.addAttribute("usuarioRelacionado", usuariosRelacionados);
        model.addAttribute("startup", startup);
        return "/detalles/detallesStartup";
    }







    @GetMapping("/crearStartup")
    public String crearStartup(Model model) {

        model.addAttribute("title", "Registrar Startup");
        model.addAttribute("startup", new StartupEntity());


        return "emprendedor/FormularioCrearStartups";
    }

    @PostMapping("/crearStartup")
    public String guardarStartup(@ModelAttribute("startup") StartupEntity startup,
                                 @RequestParam("logo1") MultipartFile imagenFile,
                                 RedirectAttributes redirectAttributes) {
        try {
            // Establecer fecha de creación
            startup.setFechaCreacion(new Date());

            // Manejar la subida de archivos
            if (!imagenFile.isEmpty()) {
                String nombreArchivo = UUID.randomUUID().toString() + "_" + imagenFile.getOriginalFilename();
                Path ruta = Paths.get("src/main/resources/static/uploads/" + nombreArchivo);
                Files.write(ruta, imagenFile.getBytes());
                startup.setLogo(nombreArchivo);  // Solo guardamos el nombre del archivo como String
            }

            startupService.save(startup);
            redirectAttributes.addFlashAttribute("mensajeExito", "Startup registrada exitosamente");
            return "redirect:/crearStartup?success";

        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error al guardar la startup o subir la imagen.");
            return "redirect:/crearStartup?error";
        }

    }







}
