package com.ensa.pe.appcontroldocumentos.app.mapper;

import com.ensa.pe.appcontroldocumentos.app.dto.req.DocumentoRequestDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.DocumentoResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.model.Documento;
import com.ensa.pe.appcontroldocumentos.app.util.FileConverter;
import org.springframework.stereotype.Component;

@Component
public class DocumentoMapper {

    private UsuarioMapper usuarioMapper;
    private TipoDocumentoMapper tipoDocumentoMapper;
    private FileConverter fileConverter;

    public DocumentoMapper(UsuarioMapper usuarioMapper, TipoDocumentoMapper tipoDocumentoMapper, FileConverter fileConverter) {
        this.usuarioMapper = usuarioMapper;
        this.tipoDocumentoMapper = tipoDocumentoMapper;
        this.fileConverter = fileConverter;
    }

    public DocumentoResponseDTO mapearADTO(Documento documento) {
        return DocumentoResponseDTO.builder()
                .codigo(documento.getIdDocumento())
                .documento(tipoDocumentoMapper.mapearADTO(documento.getTipoDocumento()))
                .fechaDocumento(documento.getFechaDocumento())
                .nroDocumento(documento.getNroDocumento())
                .remitente(documento.getRemitente())
                .direccionProcesal(documento.getDireccionProcesal())
                .suministro(documento.getSuministro())
                .referencia(documento.getReferencia())
                .descripcion(documento.getDescripcion())
                .usuarioAnterior(documento.getUsuarioAnterior())
                .usuarioAsignado(usuarioMapper.mapearAUsuarioDocumentoDTO(documento.getUsuarioAsignado()))
                .archivoAdjunto(fileConverter.convertirAByteArray(documento.getArchivoAdjunto()))
                .fechaCreacion(documento.getFechaCreacion())
                .fechaDerivacion(documento.getFechaDerivacion())
                .fechaNotificacion(documento.getFechaNotificacion())
                .fechaFinalizacion(documento.getFechaFinalizacion())
                .estado(documento.isEstado())
                .creado(documento.isCreado())
                .derivado(documento.isDerivado())
                .notificado(documento.isNotificado())
                .finalizado(documento.isFinalizado())
                .build();
    }

    public Documento mapearAEntidad(DocumentoRequestDTO documentoRequestDTO) {
        return Documento.builder()
                .idDocumento(documentoRequestDTO.getCodigo())
                .tipoDocumento(tipoDocumentoMapper.mapearAEntidadDocumento(documentoRequestDTO.getDocumento()))
                .fechaDocumento(documentoRequestDTO.getFechaDocumento())
                .nroDocumento(documentoRequestDTO.getNroDocumento())
                .remitente(documentoRequestDTO.getRemitente())
                .direccionProcesal(documentoRequestDTO.getDireccionProcesal())
                .suministro(documentoRequestDTO.getSuministro())
                .referencia(documentoRequestDTO.getReferencia())
                .descripcion(documentoRequestDTO.getDescripcion())
                .usuarioAnterior(documentoRequestDTO.getUsuarioAnterior())
                .usuarioAsignado(usuarioMapper.mapearAUsuarioDocumentoEntidad(documentoRequestDTO.getUsuarioAsignado()))
                .archivoAdjunto(fileConverter.convertirAMultipartFile(documentoRequestDTO.getArchivoAdjunto()))
                .fechaCreacion(documentoRequestDTO.getFechaCreacion())
                .fechaDerivacion(documentoRequestDTO.getFechaDerivacion())
                .fechaNotificacion(documentoRequestDTO.getFechaNotificacion())
                .fechaFinalizacion(documentoRequestDTO.getFechaFinalizacion())
                .estado(documentoRequestDTO.isEstado())
                .creado(documentoRequestDTO.isCreado())
                .derivado(documentoRequestDTO.isDerivado())
                .notificado(documentoRequestDTO.isNotificado())
                .finalizado(documentoRequestDTO.isFinalizado())
                .build();
    }
}
