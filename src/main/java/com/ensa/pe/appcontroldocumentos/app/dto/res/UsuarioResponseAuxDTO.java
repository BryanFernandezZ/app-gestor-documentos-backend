package com.ensa.pe.appcontroldocumentos.app.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioResponseAuxDTO { //TODO: CON PASSWORD
    private Long codigo;
    private String nombre;
    private String apellidos;
    private TipoIdentidadResponseDTO tipoIdentidad;
    private String nroIdentidad;
    private String telefono;
    private String correo;
    private byte[] avatar;
    private AreaResponseDTO area;
    private RolResponseDTO rol;
    private String usuario;
    private String contrasenia;
    private boolean isActive;
}
