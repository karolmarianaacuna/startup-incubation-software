package com.codewithteam.proyecto_integrador.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "MONITORIAS")

public class MonitoriaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mentoria")
    private long idMentoria;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_monitoria")
    private Date fechaMonitoria;

    @NotNull
    @Size(min = 1, max=200)
    @Column(name="tema")
    private String tema;

    @NotNull
    @Size(min = 1, max=200)
    @Column(name="observaciones")
    private String observaciones;

   //relacion de monitorias con usuarios(muchos a uno)
   @ManyToOne()
   @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
   private UsuarioEntity usuarioMonitoria;

    //relacion de monitorias con startup(muchos a uno)
    @ManyToOne()
    @JoinColumn(name = "id_startup", referencedColumnName = "id_startup")
    private StartupEntity startupMonitoria;



}
