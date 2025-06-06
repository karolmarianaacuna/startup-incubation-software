package com.codewithteam.proyecto_integrador.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Data
@Entity
@Table(name="USUARIOS")
public class UsuarioEntity  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private long idUsuario;


    @NotNull
    @Size(min = 1, max=20)
    @Column(name="nombre_usuario")
    private String nombreUsuario;

    @NotNull
    @Size(min = 1, max=20)
    @Column(name="profesion_usuario")
    private String profesionUsuario;


    @Size(min = 1, max=300)
    @Column(name="descripcion_profesional")
    private String descripcionProfesional;

    @Size(min = 1, max=300)
    @Column(name="linkedin_url")
    private String linkedinUrl;

    @Size(min = 1, max=300)
    @Column(name="github_url")
    private String githubUrl;

    @Size(min = 1, max=300)
    @Column(name="idiomas")
    private String idiomas ;



    @NotNull
    @Size(min = 1, max=20)
    @Column(name="Cargo_usuario")
    private String Cargo;


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


    @Size(min = 1, max=10)
    @Column(name ="img_usuario")
    private String imagenUsuario;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_creacion")
    private Date fechaCreacion;




    //relacion de usuarios con rol de (muchos a uno)
    @ManyToOne()
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    private RolEntity rol;

    //Relacion de usuario con Monitorias(uno a muchos)
    @OneToMany(mappedBy = "usuarioMonitoria",fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    private Collection<MonitoriaEntity> monitoriasUsuario;
}
