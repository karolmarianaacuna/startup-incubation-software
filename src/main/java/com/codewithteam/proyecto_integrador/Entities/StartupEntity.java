package com.codewithteam.proyecto_integrador.Entities;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
@Table(name = "STARTUPS")
public class StartupEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_startup")
    private long idStartup;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_startup")
    private String nombreStartup;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "correo_startup")
    private String correoStartup;

    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion_startup")
    private String descripcionStartup;

    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "etapa_startup")
    private String etapaStartup;


    @Size(min = 1, max = 500)
    @Column(name = "ubicacion_startup")
    private String ubicacionStartup;


    @Size(min = 1, max = 500)
    @Column(name = "url_paginaWeb")
    private String urlPaginaWeb;


    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "sector_startup")
    private String sectorStartup;

    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "propuesta_valor")
    private String propuestaValor;

    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "problematica")
    private String problematica;

    @Size(max = 200)
    @Column(name = "logo_startup")
    private String logo;

    @Size(max = 200)
    @Column(name = "enlace_video")
    private String video;

    //relacion de startup con convocatoria(muchos a uno)
    @ManyToOne()
    @JoinColumn(name = "id_convocatoria", referencedColumnName = "id_convocatoria")
    private ConvocatoriaEntity convocatoria;

    //Relacion de startup a mentoria (uno a muchos)
    @OneToMany(mappedBy = "startupMonitoria", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<MonitoriaEntity> monitoriasStartup;

    //relacion de startup con noticias(muchos a uno)
    @ManyToOne()
    @JoinColumn(name = "id_noticias", referencedColumnName = "id_noticias")
    private NoticiaEntity noticias;



}
