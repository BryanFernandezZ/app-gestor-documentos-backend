package com.ensa.pe.appcontroldocumentos.app.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequestDTO {
    private Long codigo;
    private String nombre;
    private String apellidos;
    private TipoIdentidadRequestDTO tipoIdentidad;
    private String nroIdentidad;
    private String telefono;
    private String correo;
    private MultipartFile avatar;
    private AreaRequestDTO area;
    private RolRequestDTO rol;
    private String usuario;
    private String contrasenia;
    private boolean isActive;
}
