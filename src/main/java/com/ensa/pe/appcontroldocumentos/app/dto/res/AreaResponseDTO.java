package com.ensa.pe.appcontroldocumentos.app.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AreaResponseDTO {
    private Long codigo;
    private String area;
}
