package com.ensa.pe.appcontroldocumentos.app.service;

import com.ensa.pe.appcontroldocumentos.app.dto.res.CategoriaResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.mapper.CategoriaMapper;
import com.ensa.pe.appcontroldocumentos.app.model.Categoria;
import com.ensa.pe.appcontroldocumentos.app.repository.CategoriaRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;
    @Mock
    private CategoriaMapper categoriaMapper;
    @InjectMocks
    private CategoriaServiceImp categoriaService;
    private Categoria categoria;

    @BeforeEach
    void setUp() {
        categoria = Categoria.builder()
                .idCategoria(Long.valueOf(100))
                .nombre("Recibos")
                .build();
    }

    @Test
    void obtenerCategorias() {
        //given
        given(categoriaRepository.findAll()).willReturn(Arrays.asList(categoria));

        //when
        List<CategoriaResponseDTO> categoriasRespuesta = categoriaService.obtenerCategorias();

        //then
        verify(categoriaRepository).findAll();
        verify(categoriaMapper).mapearADTO(categoria);

        assertThat(categoriasRespuesta).isNotNull();
        assertThat(categoriasRespuesta.size()).isGreaterThan(0);
    }

    @Test
    void verCategoria() {
        //given
        given(categoriaRepository.findById(categoria.getIdCategoria()))
                .willReturn(Optional.of(categoria));
        given(categoriaMapper.mapearADTO(categoria)).willReturn(
                CategoriaResponseDTO.builder()
                        .codigo(categoria.getIdCategoria())
                        .categoria(categoria.getNombre())
                        .build());

        //when
        CategoriaResponseDTO categoriaRespuesta = categoriaService.verCategoria(categoria.getIdCategoria());

        //then
        verify(categoriaRepository).findById(categoria.getIdCategoria());
        verify(categoriaMapper).mapearADTO(categoria);

        assertThat(categoriaRespuesta).isNotNull();
        assertThat(categoriaRespuesta.getCodigo()).isEqualTo(categoria.getIdCategoria());
    }
}