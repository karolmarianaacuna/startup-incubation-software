package com.codewithteam.proyecto_integrador.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table(name = "NOTICIAS")
public class NoticiaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_noticias")
    private long idNoticias;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_noticia")
    private String nombreNoticia;

    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion_noticia")
    private String descripcionNoticia;

    @Size(max = 200)
    @Column(name = "foto_noticia")
    private String fotoNoticia;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_creacion")
    private Date fechaCreacion;

    //Relacion de noticias a startup (uno a muchos)
    @OneToMany(mappedBy = "noticias", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<StartupEntity> startup;


}
