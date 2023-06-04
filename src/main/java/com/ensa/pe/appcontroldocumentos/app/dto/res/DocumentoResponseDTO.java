package com.ensa.pe.appcontroldocumentos.app.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentoResponseDTO {
    private Long codigo;
    private TipoDocumentoResponseDTO documento;
    private LocalDate fechaDocumento;
    private String nroDocumento;
    private String remitente;
    private String direccionProcesal;
    private String suministro;
    private String referencia;
    private String descripcion;
    private String usuarioAnterior;
    private UsuarioDocumentoResponseDTO usuarioAsignado;
    private byte[] archivoAdjunto;
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
