package com.codewithteam.proyecto_integrador.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="CONVOCATORIAS")
public class ConvocatoriaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_convocatoria")
    private int idConvocatorai;

    @NotNull
    @Size(min = 1, max=20)
    @Column(name="titulo")
    private String titulo;

    @NotNull
    @Size(min = 1, max=200)
    @Column(name="descripcion")
    private String descripcion;

    @NotNull
    @Size(min = 1, max=200)
    @Column(name="requisitos")
    private String requisitos;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_inicio")
    private Date fechaInicio;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_fin")
    private Date fechaFin;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_creacion")
    private Date fechaCreacion;

}
