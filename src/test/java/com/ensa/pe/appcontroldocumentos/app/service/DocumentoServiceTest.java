package com.ensa.pe.appcontroldocumentos.app.service;

import com.ensa.pe.appcontroldocumentos.app.dto.req.DocumentoRequestDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.DocumentoResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.UsuarioDocumentoResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.mapper.DocumentoMapper;
import com.ensa.pe.appcontroldocumentos.app.mapper.TipoDocumentoMapper;
import com.ensa.pe.appcontroldocumentos.app.model.Documento;
import com.ensa.pe.appcontroldocumentos.app.model.TipoDocumento;
import com.ensa.pe.appcontroldocumentos.app.model.Usuario;
import com.ensa.pe.appcontroldocumentos.app.repository.DocumentoRepository;
import com.ensa.pe.appcontroldocumentos.app.repository.UsuarioRepository;
import com.ensa.pe.appcontroldocumentos.app.util.FileConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DocumentoServiceTest {

    @Mock
    private DocumentoRepository documentoRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private DocumentoMapper documentoMapper;
    @Mock
    private TipoDocumentoMapper tipoDocumentoMapper;
    @Mock
    private FileConverter fileConverter;
    @InjectMocks
    private DocumentoServiceImp documentoService;

    private Documento documento;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        documento = Documento.builder()
                .idDocumento(Long.valueOf(100))
                .tipoDocumento(
                        TipoDocumento.builder()
                                .idTipoDocumento(Long.valueOf(1))
                                .build()
                )
                .fechaDocumento(LocalDate.of(2023, Calendar.APRIL, 21)) //new Date(2023, Calendar.APRIL, 21)
                .nroDocumento("GSC-EC-R67916-A-2022")
                .remitente("ROMERO PERALTA, MARLENI AIDEE")
                .direccionProcesal("Anexo LAS DELICIAS Nº POZO DE AGUA Localidad Caserio Puente Machuca")
                .suministro("27311180")
                .referencia("R67916-A-2022")
                .descripcion("Descripcion del documento nuevo")
                .usuarioAnterior(null)
                .usuarioAsignado(
                        Usuario.builder()
                                .id(Long.valueOf(1))
                                .nombre("Cristian")
                                .apellidos("Zapata Saenz")
                                .build()
                )
                .archivoAdjunto(null)
                .fechaCreacion(LocalDate.of(2023, Calendar.APRIL, 22))
                .fechaDerivacion(null)
                .fechaNotificacion(null)
                .fechaFinalizacion(null)
                .estado(true)
                .creado(true)
                .derivado(false)
                .notificado(false)
                .finalizado(false)
                .build();

        usuario = Usuario.builder()
                .id(Long.valueOf(2))
                .nombre("Brayan Robert")
                .apellidos("Fernandez Zabaleta")
                .build();
    }

    @Test
    void obtenerDocumentos() {
        //given
        given(documentoRepository.findAll()).willReturn(Arrays.asList(documento));
        given(documentoMapper.mapearADTO(documento))
                .willReturn(DocumentoResponseDTO.builder()
                        .codigo(documento.getIdDocumento())
                        .build());

        //when
        List<DocumentoResponseDTO> documentosRespuesta = documentoService.obtenerDocumentos();

        //then
        verify(documentoRepository).findAll();
        verify(documentoMapper).mapearADTO(documento);

        assertThat(documentosRespuesta).isNotNull();
        assertThat(documentosRespuesta.size()).isGreaterThan(0);
    }

    @Test
    void obtenerDocumentosPorUsuario() {
        //given
        given(documentoRepository.obtenerDocumentosPorUsuario(documento.getUsuarioAsignado().getId()))
                .willReturn(Optional.of(Arrays.asList(documento)));
        given(documentoMapper.mapearADTO(documento))
                .willReturn(DocumentoResponseDTO.builder()
                        .codigo(documento.getIdDocumento())
                        .usuarioAsignado(UsuarioDocumentoResponseDTO.builder()
                                .codigo(documento.getUsuarioAsignado().getId())
                                .build())
                        .build());

        //when
        List<DocumentoResponseDTO> documentosRespuesta = documentoService
                .obtenerDocumentosPorUsuario(documento.getUsuarioAsignado().getId());

        //then
        verify(documentoRepository).obtenerDocumentosPorUsuario(documento.getUsuarioAsignado().getId());
        verify(documentoMapper).mapearADTO(documento);

        assertThat(documentosRespuesta).isNotNull();
        assertThat(documentosRespuesta.size()).isGreaterThan(0);
        assertThat(documentosRespuesta.get(0).getUsuarioAsignado().getCodigo())
                .isEqualTo(documento.getUsuarioAsignado().getId());
    }

    @Test
    void verDocumento() {
        //given
        given(documentoRepository.findById(documento.getIdDocumento())).willReturn(Optional.of(documento));
        given(documentoMapper.mapearADTO(documento)).willReturn(DocumentoResponseDTO.builder()
                .codigo(documento.getIdDocumento())
                .build());

        //when
        DocumentoResponseDTO documentoRespuesta = documentoService.verDocumento(documento.getIdDocumento());

        //then
        verify(documentoRepository).findById(documento.getIdDocumento());
        verify(documentoMapper).mapearADTO(documento);

        assertThat(documentoRespuesta).isNotNull();
        assertThat(documentoRespuesta.getCodigo()).isEqualTo(documento.getIdDocumento());
    }

    @Test
    void crearDocumento() {
        //given
        DocumentoRequestDTO documentoGuardar = DocumentoRequestDTO.builder()
                .nroDocumento(documento.getNroDocumento())
                .descripcion(documento.getDescripcion())
                .build();

        given(documentoRepository.findByNroDocumento(documento.getNroDocumento()))
                .willReturn(Optional.empty());
        given(documentoMapper.mapearAEntidad(documentoGuardar)).willReturn(documento);

        //when
        documentoService.crearDocumento(documentoGuardar);

        //then
        verify(documentoRepository).findByNroDocumento(documentoGuardar.getNroDocumento());
        verify(documentoMapper).mapearAEntidad(documentoGuardar);
        verify(documentoRepository).save(documento);
    }

    @Test
    void actualizarDocumento() {
        //given
        DocumentoRequestDTO documentoActualizar = DocumentoRequestDTO.builder()
                .codigo(documento.getIdDocumento())
                .descripcion("Descripcion Testing")
                .build();

        given(documentoRepository.findById(documento.getIdDocumento()))
                .willReturn(Optional.of(documento));
        given(documentoMapper.mapearAEntidad(documentoActualizar)).willReturn(documento);

        //when
        documentoService.actualizarDocumento(documentoActualizar);

        //then
        verify(documentoRepository).findById(documentoActualizar.getCodigo());
        verify(documentoMapper).mapearAEntidad(documentoActualizar);
        verify(documentoRepository).saveAndFlush(documento);
    }

    @Test
    void eliminarDocumento() {
        //given
        Long id = documento.getIdDocumento();

        given(documentoRepository.findById(id)).willReturn(Optional.of(documento));

        //when
        documentoService.eliminarDocumento(id);

        //then
        verify(documentoRepository).findById(id);
        verify(documentoRepository).saveAndFlush(documento);
    }

    @Test
    void actualizarArchivoAdjunto() {
        //given
        Long id = documento.getIdDocumento();
        String imagenCodificada = getAvatar("temporal_profile_picture.jpg");
        MockMultipartFile mockFile = new MockMultipartFile("file", "imagen.jpeg",
                "image/jpeg", Base64.getDecoder().decode(imagenCodificada));

        given(documentoRepository.findById(id)).willReturn(Optional.of(documento));
        given(fileConverter.convertirAMultipartFile(mockFile)).willReturn(imagenCodificada);

        //when
        documentoService.actualizarArchivoAdjunto(id, mockFile);

        //then
        verify(documentoRepository).findById(id);
        verify(fileConverter).convertirAMultipartFile(mockFile);
        verify(documentoRepository).saveAndFlush(documento);
    }

    @Test
    void derivarDocumento() {
        //given
        Long id = documento.getIdDocumento();
        Long idUsuarioAsignado = Long.valueOf(2);

        given(documentoRepository.findById(id)).willReturn(Optional.of(documento));

        //when
        documentoService.derivarDocumento(id, idUsuarioAsignado);

        //then
        verify(documentoRepository).findById(id);
        verify(documentoRepository).saveAndFlush(documento);
    }

    @Test
    void notificarDocumento() {
        //given
        Long id = documento.getIdDocumento();
        String nombreApellidoUsuario = usuario.getNombre() + " " + usuario.getApellidos();
        String imagenCodificada = getAvatar("temporal_profile_picture.jpg");
        MockMultipartFile mockFile = new MockMultipartFile("file", "imagen.jpeg",
                "image/jpeg", Base64.getDecoder().decode(imagenCodificada));

        given(usuarioRepository.obtenerUsuarioPorNombreYApellido(nombreApellidoUsuario))
                .willReturn(Optional.of(usuario));
        given(documentoRepository.findById(id)).willReturn(Optional.of(documento));
        given(fileConverter.convertirAMultipartFile(mockFile)).willReturn(imagenCodificada);

        //when
        documentoService.notificarDocumento(id, nombreApellidoUsuario, mockFile);

        //then
        verify(usuarioRepository).obtenerUsuarioPorNombreYApellido(nombreApellidoUsuario);
        verify(documentoRepository).findById(id);
        verify(fileConverter).convertirAMultipartFile(mockFile);
        verify(documentoRepository).saveAndFlush(documento);
    }

    @Test
    void finalizarDocumento() {
        //given
        Long id = documento.getIdDocumento();

        given(documentoRepository.findById(id)).willReturn(Optional.of(documento));

        //when
        documentoService.finalizarDocumento(id);

        //then
        verify(documentoRepository).findById(id);
        verify(documentoRepository).saveAndFlush(documento);
    }

    private Path obtenerMediaFilesPath() {
        //Ruta raiz
        String mediaFilesLocation = "mediafiles";
        Path rootLocation = Paths.get(mediaFilesLocation);
        return rootLocation;
    }

    private String getAvatar(String fileName) {
        try {
            Path file = obtenerMediaFilesPath().resolve(fileName);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                byte[] bytes = resource.getInputStream().readAllBytes();
                return Base64.getEncoder().encodeToString(bytes);
            } else {
                throw new RuntimeException("El archivo no puede leerse o no existe");
            }

        } catch (IOException e) {
            throw new RuntimeException("El archivo no puede leerse o no existe");
        }
    }
}