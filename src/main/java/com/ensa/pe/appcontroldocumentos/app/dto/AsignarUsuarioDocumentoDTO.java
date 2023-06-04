package com.ensa.pe.appcontroldocumentos.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignarUsuarioDocumentoDTO {
    private Long idDocumento;
    private Long idUsuarioAsignado;
}
