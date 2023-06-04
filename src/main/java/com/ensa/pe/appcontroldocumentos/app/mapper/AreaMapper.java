package com.ensa.pe.appcontroldocumentos.app.mapper;

import com.ensa.pe.appcontroldocumentos.app.dto.req.AreaRequestDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.AreaResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.model.Area;
import org.springframework.stereotype.Component;

@Component
public class AreaMapper {

    public AreaResponseDTO mapearADTO(Area area) {
        return AreaResponseDTO.builder()
                .codigo(area.getId())
                .area(area.getNombre())
                .build();
    }

    public Area mapearAEntidad(AreaRequestDTO areaRequestDTO) {
        return Area.builder()
                .id(areaRequestDTO.getCodigo())
                .nombre(areaRequestDTO.getArea())
                .build();
    }
}
