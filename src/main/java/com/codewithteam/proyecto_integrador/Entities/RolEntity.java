package com.codewithteam.proyecto_integrador.Entities;


import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Data
@Entity
@Table(name = "ROLES")

public class RolEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;

    @NotNull
    @Size(min =1, max = 40)
    @Column(name = "rol", length = 49, nullable = false)
    private String Rol;

    public RolEntity(String Rol) {
        super();
        this.Rol = Rol;
    }

    public RolEntity() {}

    @OneToMany(mappedBy = "rol")
    private List<UsuarioEntity> usuarios;


}
