package com.ensa.pe.appcontroldocumentos.app.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoDocumentoRequestDTO {
    private Long codigo;
    private String tipoDocumento;
    private CategoriaRequestDTO categoria;
}
