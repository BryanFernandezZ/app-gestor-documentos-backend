package com.ensa.pe.appcontroldocumentos.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tipo_documentos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoDocumento;
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "id_categoria",
            foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_categoria) references categorias(id_categoria)")
    )
    private Categoria categoria;

    @OneToMany(mappedBy = "tipoDocumento", fetch = FetchType.LAZY)
    private List<Documento> documentos = new ArrayList<>();
}
