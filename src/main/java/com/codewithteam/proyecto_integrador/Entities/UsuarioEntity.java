package com.codewithteam.proyecto_integrador.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="USUARIOS")
public class UsuarioEntity  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id_usuario")
    private int idUsuario;

    @NotNull
    @Size(min = 1, max=20)
    @Column(name="nombre_usuario")
    private String name;

    @NotNull
    @Size(min = 1, max=50)
    @Column(name = "correo_usuario", nullable = false)
    private String correoUsuario;

    @NotNull
    @Size(min = 1, max=20)
    @Column(name = "contrasena_usuario", nullable = false)
    private String contrasenaUsuario;

    @NotNull
    @Size(min = 1, max=10)
    @Column(name = "celular_usuario", nullable = false)
    private String celularUsuario;

    @NotNull
    @Size(min = 1, max=10)
    @Column(name ="identificacion_usuario", unique = true)
    private String identificacionUsuario;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_creacion")
    private Date fechaCreacion;

    @NotNull
    @Size(min=1, max=200)
    @Column(name = "foto_usuario", length = 200)
    private String fotoUsuario;

}
