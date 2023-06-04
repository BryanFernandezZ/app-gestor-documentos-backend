package com.ensa.pe.appcontroldocumentos.app.mapper;

import com.ensa.pe.appcontroldocumentos.app.dto.req.RolRequestDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.RolResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.model.Rol;
import org.springframework.stereotype.Component;

@Component
public class RolMapper {

    public RolResponseDTO mapearADTO(Rol rol) {
        return RolResponseDTO.builder()
                .codigo(rol.getId())
                .rol(rol.getNombre())
                .build();
    }

    public Rol mapearAEntidad(RolRequestDTO rolRequestDTO) {
        return Rol.builder()
                .id(rolRequestDTO.getCodigo())
                .nombre(rolRequestDTO.getRol())
                .build();
    }
}
