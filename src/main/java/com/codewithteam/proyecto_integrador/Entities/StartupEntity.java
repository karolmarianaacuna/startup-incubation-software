package com.codewithteam.proyecto_integrador.Entities;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "STARTUPS")
public class StartupEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_startup")
    private int idStartup;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_startup")
    private String nombreStartup;

    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion_startup")
    private String descripcionStartup;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "sector_startup")
    private String sectorStar;

    @Size(max = 200)
    @Column(name = "logo_startup")
    private String logo; 


}
