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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class startupController {

    @Autowired
    private StartupService startupService;
    @Autowired
    private MonitoriaService monitoriaService;

    @GetMapping("/startups/{id}")
    public String verStartup(@PathVariable Long id, Model model) {

        StartupEntity startup = startupService.viewDetail(id);
        List<UsuarioEntity> usuariosRelacionados = monitoriaService.encontrarUsuariosPorStartup(id);


        model.addAttribute("usuarioRelacionado", usuariosRelacionados);
        model.addAttribute("startup", startup);
        return "/detalles/detallesStartup";
    }



    @GetMapping("/pruebaStartups")
    public String Startups(@RequestParam(required = false) String categoria, Model model) {


        List<StartupEntity> startups;


        if (categoria != null && !categoria.isEmpty()) {
            startups = startupService.findByCategoria(categoria);

            model.addAttribute("categoriaSeleccionada", categoria);
        } else {
            startups = startupService.findAll();
        }

        model.addAttribute("startups", startups);

        System.out.println("Categoría seleccionada: " + categoria);

        return "/startups/startups";
    }

    @GetMapping("/crearStartup")
    public String crearSartup(Model model){

        model.addAttribute("title", "Registrar Startup");
        model.addAttribute("startupEntity",new StartupEntity());


        return "/registroInicio/registroStartup";
    }

    @PostMapping("/crearStartup")
    public String guardarStartup(@Valid StartupEntity startupEntity, @RequestParam(value = "foto") MultipartFile foto, BindingResult result, RedirectAttributes redirectAttributes) {
//        String urlImagen = guardarImagen(foto);
//        if (result.hasErrors()) {
//            System.out.println(result.getAllErrors());
//            return "habitaciones/crearHabitacion";
//
//        }
//        habitacion.setFotoHab(urlImagen);
//        habitacion.setDisponibilidad(true);
//        habitacionService.save(habitacion);
//        redirectAttributes.addFlashAttribute("mensajeExito", "Room Saved Successfully");
        return "redirect:/habitacionesProtalPrueba";
    }


    private String guardarImagen(MultipartFile imagen) {
        try{
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://api.imgbb.com/1/upload");

            MultipartEntityBuilder builder = MultipartEntityBuilder.create().create();
            builder.addTextBody("key","e38b77d8e3d41a6e26e71e1afc339ec1",
                    ContentType.TEXT_PLAIN);
            builder.addBinaryBody("image", imagen.getInputStream(),
                    ContentType.DEFAULT_BINARY,imagen.getOriginalFilename());

            HttpEntity multipart = builder.build();
            httpPost.setEntity(multipart);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == 200) {
                String responseString = EntityUtils.toString(responseEntity);
                JSONObject jsonResponse = new JSONObject(responseString);
                boolean success = jsonResponse.getBoolean("success");
                if (success) {
                    JSONObject data = jsonResponse.getJSONObject("data");
                    return data.getString("url");


                }else {
                    System.err.println("Error loading Image"+ jsonResponse.optString("error", "Unknown Error"));
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }







}
