package com.ensa.pe.appcontroldocumentos.app.mapper;

import com.ensa.pe.appcontroldocumentos.app.dto.req.CategoriaRequestDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.CategoriaResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {
    public CategoriaResponseDTO mapearADTO(Categoria categoria){
        return CategoriaResponseDTO.builder()
                .codigo(categoria.getIdCategoria())
                .categoria(categoria.getNombre())
                .build();
    }

    public Categoria mapearAEntidad(CategoriaRequestDTO categoriaRequestDTO){
        return Categoria.builder()
                .idCategoria(categoriaRequestDTO.getCodigo())
                .nombre(categoriaRequestDTO.getCategoria())
                .build();
    }
}
