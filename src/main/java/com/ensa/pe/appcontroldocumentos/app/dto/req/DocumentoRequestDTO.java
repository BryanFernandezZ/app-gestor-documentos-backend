package com.ensa.pe.appcontroldocumentos.app.dto.req;

import com.ensa.pe.appcontroldocumentos.app.dto.res.UsuarioDocumentoResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentoRequestDTO {
    private Long codigo;
    private TipoDocumentoRequestDTO documento;
    private LocalDate fechaDocumento;
    private String nroDocumento;
    private String remitente;
    private String direccionProcesal;
    private String suministro;
    private String referencia;
    private String descripcion;
    private String usuarioAnterior;
    private UsuarioRequestDTO usuarioAsignado;
    private MultipartFile archivoAdjunto;
    private LocalDate fechaCreacion;
    private LocalDate fechaDerivacion;
    private LocalDate fechaNotificacion;
    private LocalDate fechaFinalizacion;
    private boolean estado;
    private boolean creado;
    private boolean derivado;
    private boolean notificado;
    private boolean finalizado;

//    private String documento;
}
