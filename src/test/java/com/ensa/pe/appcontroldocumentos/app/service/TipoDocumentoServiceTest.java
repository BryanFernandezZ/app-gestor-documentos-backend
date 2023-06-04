package com.ensa.pe.appcontroldocumentos.app.service;

import com.ensa.pe.appcontroldocumentos.app.dto.req.CategoriaRequestDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.req.TipoDocumentoRequestDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.TipoDocumentoResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.mapper.TipoDocumentoMapper;
import com.ensa.pe.appcontroldocumentos.app.model.Categoria;
import com.ensa.pe.appcontroldocumentos.app.model.TipoDocumento;
import com.ensa.pe.appcontroldocumentos.app.repository.TipoDocumentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TipoDocumentoServiceTest {

    @Mock
    private TipoDocumentoRepository tipoDocumentoRepository;
    @Mock
    private TipoDocumentoMapper tipoDocumentoMapper;
    @InjectMocks
    private TipoDocumentoServiceImp tipoDocumentoService;
    private TipoDocumento tipoDocumento;

    @BeforeEach
    void setUp() {
        tipoDocumento = TipoDocumento.builder()
                .nombre("Recibo de Prueba")
                .categoria(Categoria.builder().idCategoria(Long.valueOf(100)).build())
                .build();
    }

    @Test
    void obtenerTiposDocumento() {
        //given
        given(tipoDocumentoRepository.findAll()).willReturn(Arrays.asList(tipoDocumento));

        //when
        List<TipoDocumentoResponseDTO> tipoDocumentosRespuesta = tipoDocumentoService.obtenerTiposDocumento();

        //then
        verify(tipoDocumentoRepository).findAll();
        verify(tipoDocumentoMapper).mapearADTO(tipoDocumento);

        assertThat(tipoDocumentosRespuesta).isNotNull();
        assertThat(tipoDocumentosRespuesta.size()).isGreaterThan(0);
    }

    @Test
    void guardarTipoDocumento() {
        //given
        TipoDocumentoRequestDTO request = TipoDocumentoRequestDTO.builder()
                .tipoDocumento(tipoDocumento.getNombre())
                .categoria(CategoriaRequestDTO.builder()
                        .codigo(tipoDocumento.getCategoria().getIdCategoria())
                        .build())
                .build();

        given(tipoDocumentoMapper.mapearAEntidad(request)).willReturn(tipoDocumento);

        //when
        tipoDocumentoService.guardarTipoDocumento(request);

        //then
        verify(tipoDocumentoMapper).mapearAEntidad(request);
        verify(tipoDocumentoRepository).save(tipoDocumento);
    }
}