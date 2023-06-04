package com.ensa.pe.appcontroldocumentos.app.service;

import com.ensa.pe.appcontroldocumentos.app.dto.req.UsuarioRequestDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.UsuarioResponseAuxDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.UsuarioResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.mapper.UsuarioMapper;
import com.ensa.pe.appcontroldocumentos.app.model.Usuario;
import com.ensa.pe.appcontroldocumentos.app.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private UsuarioMapper usuarioMapper;
    @InjectMocks
    private UsuarioServiceImp usuarioService;
    private Usuario usuario;
    private UsuarioRequestDTO usuarioRequestDTO;

    @BeforeEach
    void setUp() {
        usuario = Usuario.builder()
                .id(Long.valueOf(1))
                .nombre("Maria")
                .apellidos("Becerra Diaz")
                .nroIdentidad("74581236")
                .telefono("948125365")
                .correo("maria@gmail.com")
                .usuario("maria")
                .isActive(true)
                .build();

        usuarioRequestDTO = UsuarioRequestDTO.builder()
                .codigo(usuario.getId())
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .nroIdentidad(usuario.getNroIdentidad())
                .telefono(usuario.getTelefono())
                .correo(usuario.getCorreo())
                .usuario(usuario.getUsuario())
                .isActive(usuario.isActive())
                .build();
    }

    @Test
    void obtenerUsuarios() {
        //given
        given(usuarioRepository.findAll()).willReturn(Arrays.asList(usuario));
        given(usuarioMapper.mapearADTO(usuario)).willReturn(UsuarioResponseDTO.builder()
                .codigo(usuario.getId())
                .build());
        //when
        List<UsuarioResponseDTO> usuariosRespuesta = usuarioService.obtenerUsuarios();
        //then
        verify(usuarioRepository).findAll();
        verify(usuarioMapper).mapearADTO(usuario);

        assertThat(usuariosRespuesta).isNotNull();
        assertThat(usuariosRespuesta.size()).isEqualTo(1);
        assertThat(usuariosRespuesta.get(0).getCodigo()).isEqualTo(usuario.getId());
    }

    @Test
    void verUsuario() {
        //given
        given(usuarioRepository.findById(usuario.getId())).willReturn(Optional.of(usuario));
        given(usuarioMapper.mapearADTO(usuario)).willReturn(UsuarioResponseDTO.builder()
                .codigo(usuario.getId())
                .build());
        //when
        UsuarioResponseDTO usuarioRespuesta = usuarioService.verUsuario(usuario.getId());
        //then
        verify(usuarioRepository).findById(usuario.getId());
        verify(usuarioMapper).mapearADTO(usuario);

        assertThat(usuarioRespuesta).isNotNull();
        assertThat(usuarioRespuesta.getCodigo()).isEqualTo(usuario.getId());
    }

    @Test
    void guardarUsuario() {
        //given
        given(usuarioMapper.mapearAEntidad(usuarioRequestDTO)).willReturn(usuario);
        given(usuarioRepository.save(usuario)).willReturn(usuario);
        given(usuarioRepository.findById(usuario.getId())).willReturn(Optional.of(usuario));

        //when
        usuarioService.guardarUsuario(usuarioRequestDTO);

        //TODO: Buscando el usuario guardado ya que el metodo del service retorna null
        Usuario usuarioGuardado = usuarioRepository.findById(usuario.getId()).orElse(null);

        //then
        verify(usuarioRepository).save(usuario);

        assertThat(usuarioGuardado).isNotNull();
        assertThat(usuarioGuardado.getId()).isEqualTo(usuarioRequestDTO.getCodigo());
    }

    @Test
    void actualizarUsuario() {
        //TODO: Actualizando el campo isActive de true a false para la prueba
        //given

        Usuario usuarioActualizado = Usuario.builder()
                .id(Long.valueOf(1))
                .nombre("Maria")
                .apellidos("Becerra Diaz")
                .nroIdentidad("74581236")
                .telefono("948125365")
                .correo("maria@outlook.com")
                .usuario("maria")
                .isActive(true)
                .build();

        given(usuarioMapper.mapearAEntidad(usuarioRequestDTO)).willReturn(usuario);
        given(usuarioRepository.saveAndFlush(usuario)).willReturn(usuarioActualizado);
        given(usuarioRepository.findById(usuario.getId())).willReturn(Optional.of(usuarioActualizado));

        //when
        usuarioService.actualizarUsuario(usuarioRequestDTO);

        //TODO: Buscando el usuario guardado ya que el metodo del service retorna null
        Usuario usuarioGuardado = usuarioRepository.findById(usuario.getId()).orElse(null);

        //then
        verify(usuarioRepository).saveAndFlush(usuario);

        assertThat(usuarioGuardado).isNotNull();
        assertThat(usuarioGuardado.getId()).isEqualTo(usuarioRequestDTO.getCodigo());
        assertThat(usuarioGuardado.getCorreo()).isEqualTo("maria@outlook.com");
    }

    @Test
    void eliminarUsuario() {
        //TODO: Actualizando el campo isActive de true a false para la prueba
        //given
        Usuario usuarioEliminado = Usuario.builder()
                .id(Long.valueOf(1))
                .nombre("Maria")
                .apellidos("Becerra Diaz")
                .nroIdentidad("74581236")
                .telefono("948125365")
                .correo("maria@gmail.com")
                .usuario("maria")
                .isActive(false)
                .build();

        given(usuarioMapper.mapearAEntidad(usuarioRequestDTO)).willReturn(usuario);
        given(usuarioRepository.saveAndFlush(usuario)).willReturn(usuarioEliminado);
        given(usuarioRepository.findById(usuario.getId())).willReturn(Optional.of(usuarioEliminado));

        //when
        usuarioService.actualizarUsuario(usuarioRequestDTO);

        //TODO: Buscando el usuario guardado ya que el metodo del service retorna null
        Usuario usuarioGuardado = usuarioRepository.findById(usuario.getId()).orElse(null);

        //then
        verify(usuarioRepository).saveAndFlush(usuario);

        assertThat(usuarioGuardado).isNotNull();
        assertThat(usuarioGuardado.getId()).isEqualTo(usuarioRequestDTO.getCodigo());
        assertThat(usuarioGuardado.isActive()).isFalse();
    }

    @Test
    void verUsuarioAux() {
        //given
        given(usuarioRepository.findById(usuario.getId())).willReturn(Optional.of(usuario));
        given(usuarioMapper.mapearAAuxDTO(usuario))
                .willReturn(UsuarioResponseAuxDTO.builder()
                        .codigo(usuario.getId())
                        .build());
        //when
        UsuarioResponseAuxDTO usuarioRespuestaAuxDTO = usuarioService.verUsuarioAux(usuario.getId());

        //then
        verify(usuarioRepository).findById(usuario.getId());
        verify(usuarioMapper).mapearAAuxDTO(usuario);

        assertThat(usuarioRespuestaAuxDTO).isNotNull();
        assertThat(usuarioRespuestaAuxDTO.getCodigo()).isEqualTo(usuario.getId());
    }

    @Test
    void obtenerUsuariosDeArea() {
        //given
        given(usuarioRepository.obtenerUsuariosDeArea(Long.valueOf(1))).willReturn(Arrays.asList(usuario));
        given(usuarioMapper.mapearAAuxDTO(usuario))
                .willReturn(UsuarioResponseAuxDTO.builder()
                        .codigo(usuario.getId())
                        .build());

        //when
        List<UsuarioResponseAuxDTO> usuariosRespuesta = usuarioService.obtenerUsuariosDeArea(Long.valueOf(1));

        //then
        verify(usuarioRepository).obtenerUsuariosDeArea(Long.valueOf(1));
        verify(usuarioMapper).mapearAAuxDTO(usuario);

        assertThat(usuariosRespuesta).isNotNull();
        assertThat(usuariosRespuesta.size()).isGreaterThan(0);
    }
}