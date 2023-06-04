package com.ensa.pe.appcontroldocumentos.app.repository;

import com.ensa.pe.appcontroldocumentos.app.TestSecurityConfig;
import com.ensa.pe.appcontroldocumentos.app.model.Area;
import com.ensa.pe.appcontroldocumentos.app.model.Rol;
import com.ensa.pe.appcontroldocumentos.app.model.TipoIdentidad;
import com.ensa.pe.appcontroldocumentos.app.model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = { TestSecurityConfig.class })
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    private Usuario usuario;

    @BeforeEach
    void setUp(){
        usuario = Usuario.builder()
                .nombre("Maria")
                .apellidos("Becerra Diaz")
                .area(Area.builder().id(Long.valueOf(1)).build())
                .rol(Rol.builder().id(Long.valueOf(1)).build())
                .tipoIdentidad(TipoIdentidad.builder().id(Long.valueOf(1)).build())
                .build();
    }

    @AfterEach
    void cleanAll(){
        usuario = new Usuario();
    }

    @Test
    void findByUsuarioTest(){
        //given
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        //when
        String username = usuarioGuardado.getUsuario();
        Usuario usuarioRespuesta = usuarioRepository.findByUsuario(username)
                .orElse(null);

        //then
        assertThat(usuario).isNotNull();
        assertThat(usuarioGuardado.getId()).isEqualTo(usuarioRespuesta.getId());
    }

    @Test
    void obtenerUsuariosDeAreaTest() {
        //given
        long areaId = Long.valueOf(1);

        //when
        List<Usuario> usuarios = usuarioRepository.obtenerUsuariosDeArea(areaId);

        //then
        assertThat(usuarios.size()).isNotEqualTo(0);
    }

    @Test
    void obtenerUsuarioPorNombreYApellidoTest() {
        //given
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        //when
        String nombreApellidoUsuario = usuario.getNombre() + " " + usuario.getApellidos();
        Usuario usuarioRespuesta = usuarioRepository.obtenerUsuarioPorNombreYApellido(nombreApellidoUsuario)
                .orElse(null);

        assertThat(usuarioRespuesta).isNotNull();
        assertThat(usuarioGuardado.getId()).isEqualTo(usuarioRespuesta.getId());
    }
}