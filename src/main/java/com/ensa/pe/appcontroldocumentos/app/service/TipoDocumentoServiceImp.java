package com.ensa.pe.appcontroldocumentos.app.service;

import com.ensa.pe.appcontroldocumentos.app.dto.req.TipoDocumentoRequestDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.TipoDocumentoResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.mapper.TipoDocumentoMapper;
import com.ensa.pe.appcontroldocumentos.app.model.TipoDocumento;
import com.ensa.pe.appcontroldocumentos.app.repository.TipoDocumentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoDocumentoServiceImp implements TipoDocumentoService{

    private TipoDocumentoRepository tipoDocumentoRepository;
    private TipoDocumentoMapper tipoDocumentoMapper;

    //TODO: DEPENDENCY INJECTION
    public TipoDocumentoServiceImp(TipoDocumentoRepository tipoDocumentoRepository, TipoDocumentoMapper tipoDocumentoMapper) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
        this.tipoDocumentoMapper = tipoDocumentoMapper;
    }

    @Override
    public List<TipoDocumentoResponseDTO> obtenerTiposDocumento() {
        List<TipoDocumento> tipoDocumentosDb = tipoDocumentoRepository.findAll();
        List<TipoDocumentoResponseDTO> tipoDocumentoResponse = tipoDocumentosDb.stream().map(tipoDocumentoMapper::mapearADTO)
                .collect(Collectors.toList());
        return tipoDocumentoResponse;
    }

    @Override
    public void guardarTipoDocumento(TipoDocumentoRequestDTO tipoDocumentoRequestDTO) {
        TipoDocumento tipoDocumento = tipoDocumentoMapper.mapearAEntidad(tipoDocumentoRequestDTO);
        tipoDocumentoRepository.save(tipoDocumento);
    }
}
