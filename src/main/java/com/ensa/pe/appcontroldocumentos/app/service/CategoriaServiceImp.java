package com.ensa.pe.appcontroldocumentos.app.service;

import com.ensa.pe.appcontroldocumentos.app.dto.res.CategoriaResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.exceptions.NotFoundException;
import com.ensa.pe.appcontroldocumentos.app.mapper.CategoriaMapper;
import com.ensa.pe.appcontroldocumentos.app.model.Categoria;
import com.ensa.pe.appcontroldocumentos.app.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImp implements CategoriaService {

    private CategoriaRepository repository;
    private CategoriaMapper categoriaMapper;

    //TODO: DEPENDENCY INJECTION
    public CategoriaServiceImp(CategoriaRepository repository, CategoriaMapper categoriaMapper) {
        this.repository = repository;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public List<CategoriaResponseDTO> obtenerCategorias() {
        List<Categoria> categoriasDb = repository.findAll();
        List<CategoriaResponseDTO> categoriaResponse = categoriasDb.stream().map(categoriaMapper::mapearADTO)
                .collect(Collectors.toList());
        return categoriaResponse;
    }

    @Override
    public CategoriaResponseDTO verCategoria(Long id) {
        Categoria categoriaDb = repository.findById(id).orElseThrow(() -> new NotFoundException("Categoria no encontrada"));
        CategoriaResponseDTO categoriaResponse = categoriaMapper.mapearADTO(categoriaDb);
        return categoriaResponse;
    }
}
