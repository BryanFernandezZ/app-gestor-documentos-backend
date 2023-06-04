package com.ensa.pe.appcontroldocumentos.app.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaRequestDTO {
    private Long codigo;
    private String categoria;
}
