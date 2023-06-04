package com.ensa.pe.appcontroldocumentos.app.mapper;

import com.ensa.pe.appcontroldocumentos.app.dto.req.TipoDocumentoRequestDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.TipoDocumentoResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.model.TipoDocumento;
import org.springframework.stereotype.Component;

@Component
public class TipoDocumentoMapper {

    private CategoriaMapper categoriaMapper;

    //TODO: DEPENDENCY INJECTION
    public TipoDocumentoMapper(CategoriaMapper categoriaMapper) {
        this.categoriaMapper = categoriaMapper;
    }

    public TipoDocumentoResponseDTO mapearADTO(TipoDocumento tipoDocumento) {
        return TipoDocumentoResponseDTO.builder()
                .codigo(tipoDocumento.getIdTipoDocumento())
                .tipoDocumento(tipoDocumento.getNombre())
                .categoria(categoriaMapper.mapearADTO(tipoDocumento.getCategoria()))
                .build();
    }

    public TipoDocumento mapearAEntidad(TipoDocumentoRequestDTO tipoDocumentoRequestDTO){
        return TipoDocumento.builder()
                .idTipoDocumento(tipoDocumentoRequestDTO.getCodigo())
                .nombre(tipoDocumentoRequestDTO.getTipoDocumento())
                .categoria(categoriaMapper.mapearAEntidad(tipoDocumentoRequestDTO.getCategoria()))
                .build();
    }

    public TipoDocumento mapearAEntidadDocumento(TipoDocumentoRequestDTO tipoDocumentoRequestDTO){
        return TipoDocumento.builder()
                .idTipoDocumento(tipoDocumentoRequestDTO.getCodigo())
                .nombre(tipoDocumentoRequestDTO.getTipoDocumento())
                .build();
    }
}
