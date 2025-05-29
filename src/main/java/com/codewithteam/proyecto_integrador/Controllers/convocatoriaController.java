package com.codewithteam.proyecto_integrador.Controllers;

import com.codewithteam.proyecto_integrador.Entities.ConvocatoriaEntity;
import com.codewithteam.proyecto_integrador.Models.Service.ConvocatoriaService;
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
import java.util.List;

@Controller
public class convocatoriaController {


    //siempre se le hace una inyeccion de el servicio al controlador

    @Autowired
    private ConvocatoriaService convocatoriaService;

    @GetMapping("/Convocatorias")
    public String listarConvocatoria(Model model) {

        List<ConvocatoriaEntity> lista = convocatoriaService.findAll();
        model.addAttribute("convocatorias", lista);

        return "/convocatoria/convocatoria";
    }
    @PostMapping("/eliminarConvocatoria/{id}")
    public String eliminarConvocatoria(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        boolean eliminado = convocatoriaService.deleteByIdIfNoStartups(id);

        if (eliminado) {
            redirectAttributes.addFlashAttribute("success", "Convocatoria eliminada correctamente.");
        } else {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar la convocatoria " + id + " porque tiene startups asociadas.");
        }
        return "redirect:/pruebaAdministrador"; // Redirige de vuelta a la lista
    }

    @GetMapping("/crearConvocatoria")
    public String crearConvocatoria(Model model) {
        model.addAttribute("title", "Regitrar Convocatoria");
        model.addAttribute("convocatoria", new ConvocatoriaEntity());
        return "/convocatoria/FormConvocatorias";
    }

    @PostMapping("/crearConvocatoria")
    public String guardarConvocatoria(@Valid ConvocatoriaEntity convocatoria, BindingResult result,
                                      @RequestParam("foto") MultipartFile foto,
                                      RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Error en los datos del formulario.");
            return "redirect:/crearConvocatoria";
        }

        String urlImagen = guadarImagen(foto);
        if (urlImagen == null) {
            redirectAttributes.addFlashAttribute("error", "Error al subir la imagen de la convocatoria.");
            return "redirect:/crearConvocatoria";
        }
        convocatoria.setFotoConvocatoria(urlImagen);
        convocatoria.setEstadoConvocatoria("Activa");
        convocatoriaService.save(convocatoria);
        redirectAttributes.addFlashAttribute("success", "Convocatoria creada exitosamente.");
        return "redirect:/Convocatorias";
    }
    private String guadarImagen(MultipartFile imagen) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://api.imgbb.com/1/upload");
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("key" ,"7f3a130799d25622641b26a228105bb8", ContentType.TEXT_PLAIN);
            builder.addBinaryBody(  "image",imagen.getInputStream(),ContentType.DEFAULT_BINARY, imagen.getOriginalFilename());
            HttpEntity multiPart = builder.build();
            httpPost.setEntity(multiPart);

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            if (response.getStatusLine().getStatusCode() == 200) {
                String responseString = EntityUtils.toString(entity);
                JSONObject jsonResponse = new JSONObject(responseString);
                boolean success = jsonResponse.getBoolean("success");
                if (success) {
                    JSONObject data = jsonResponse.getJSONObject("data");
                    return data.getString("url");
                }else {
                    System.out.println("Error loading image: " + jsonResponse.optString("Unknown error"));
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }









}
