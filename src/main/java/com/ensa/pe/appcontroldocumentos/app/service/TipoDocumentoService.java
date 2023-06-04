package com.ensa.pe.appcontroldocumentos.app.service;

import com.ensa.pe.appcontroldocumentos.app.dto.req.TipoDocumentoRequestDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.TipoDocumentoResponseDTO;

import java.util.List;

public interface TipoDocumentoService {
    List<TipoDocumentoResponseDTO> obtenerTiposDocumento();
    void guardarTipoDocumento(TipoDocumentoRequestDTO tipoDocumentoRequestDTO);
}
