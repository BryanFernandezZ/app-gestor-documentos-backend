package com.ensa.pe.appcontroldocumentos.app.service;

import com.ensa.pe.appcontroldocumentos.app.dto.req.DocumentoRequestDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.DocumentoResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentoService {
    List<DocumentoResponseDTO> obtenerDocumentos();
    List<DocumentoResponseDTO> obtenerDocumentosPorUsuario(Long codigo);
    DocumentoResponseDTO verDocumento(Long id);
    void crearDocumento(DocumentoRequestDTO documentoRequestDTO);
    void actualizarDocumento(DocumentoRequestDTO documentoRequestDTO);
    void eliminarDocumento(Long id);
    void actualizarArchivoAdjunto(Long id, MultipartFile archivoAdjunto);
    void derivarDocumento(Long idDocumento, Long idUsuarioAsignado);
    void notificarDocumento(Long idDocumento, String nombreApellidoUsuario, MultipartFile file);
    void finalizarDocumento(Long idDocumento);

    int obtenerCantidadDocumentos();
}
