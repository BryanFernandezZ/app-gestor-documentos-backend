package com.ensa.pe.appcontroldocumentos.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "documentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocumento;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_tipo_documento",
            foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_tipo_documento) references tipo_documentos(id_tipo_documentos)"))
    private TipoDocumento tipoDocumento;
    @Column(columnDefinition = "DATE")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaDocumento;
    @Column(length = 30, unique = true)
    private String nroDocumento;
    @Column(length = 50)
    private String remitente;
    @Column(length = 100)
    private String direccionProcesal;
    @Column(length = 30)
    private String suministro;
    @Column(length = 40)
    private String referencia;
    private String descripcion;
    private String usuarioAnterior;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario")
    private Usuario usuarioAsignado;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String archivoAdjunto;
    @Column(columnDefinition = "DATE")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaCreacion;
    @Column(columnDefinition = "DATE")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaDerivacion;
    @Column(columnDefinition = "DATE")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaNotificacion;
    @Column(columnDefinition = "DATE")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaFinalizacion;
    private boolean estado;
    private boolean creado;
    private boolean derivado;
    private boolean notificado;
    private boolean finalizado;
}
