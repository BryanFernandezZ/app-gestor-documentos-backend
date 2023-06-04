package com.ensa.pe.appcontroldocumentos.app.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDocumentoResponseDTO {
    private Long codigo;
    private String nombre;
    private String apellidos;
}
