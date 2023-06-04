package com.ensa.pe.appcontroldocumentos.app.mapper;

import com.ensa.pe.appcontroldocumentos.app.dto.res.TipoIdentidadResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.model.TipoIdentidad;
import org.springframework.stereotype.Component;

@Component
public class TipoIdentidadMapper {

    public TipoIdentidadResponseDTO mapearADTO(TipoIdentidad tipoIdentidad) {
        return TipoIdentidadResponseDTO.builder()
                .codigo(tipoIdentidad.getId())
                .tipoIdentidad(tipoIdentidad.getNombre())
                .build();
    }

    public TipoIdentidad mapearAEntidad(com.ensa.pe.appcontroldocumentos.app.dto.req.TipoIdentidadRequestDTO tipoIdentidadRequestDTO){
        return TipoIdentidad.builder()
                .id(tipoIdentidadRequestDTO.getCodigo())
                .nombre(tipoIdentidadRequestDTO.getTipoIdentidad())
                .build();
    }
}
