package com.cjrp.base_001_app_todoist.dto;

import com.cjrp.base_001_app_todoist.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

// Para enviar y recibir datos de usuario
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private Date fecha;
    private Role role;
}
