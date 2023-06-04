package com.ensa.pe.appcontroldocumentos.app.service;

import com.ensa.pe.appcontroldocumentos.app.dto.res.CategoriaResponseDTO;

import java.util.List;

public interface CategoriaService {
    List<CategoriaResponseDTO> obtenerCategorias();
    CategoriaResponseDTO verCategoria(Long id);
}
