package com.ensa.pe.appcontroldocumentos.app.repository;

import com.ensa.pe.appcontroldocumentos.app.TestSecurityConfig;
import com.ensa.pe.appcontroldocumentos.app.model.Documento;
import com.ensa.pe.appcontroldocumentos.app.model.TipoDocumento;
import com.ensa.pe.appcontroldocumentos.app.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = { TestSecurityConfig.class })
public class DocumentoRepositoryTest {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Test
    void obtenerDocumentosPorUsuario(){
        //given
        long idusuario = Long.valueOf(1);

        //when
        List<Documento> documentos = documentoRepository.obtenerDocumentosPorUsuario(idusuario)
                .orElse(Arrays.asList());

        //then
        assertThat(documentos.size()).isNotEqualTo(0);
    }

    @Test
    void findByNroDocumentoTest(){
        //given
        Documento documento = Documento.builder()
                .tipoDocumento(TipoDocumento.builder().idTipoDocumento(Long.valueOf(1)).build())
                .usuarioAsignado(Usuario.builder().id(Long.valueOf(1)).build())
                .nroDocumento("GSC-EC-R67916-C-2020")
                .build();

        Documento documentoGuardado = documentoRepository.save(documento);

        //when
        String nroDocumento = documentoGuardado.getNroDocumento();
        Documento documentoRespuesta = documentoRepository.findByNroDocumento(nroDocumento)
                .orElse(null);

        //then
        assertThat(documentoRespuesta).isNotNull();
        assertThat(documentoRespuesta.getIdDocumento()).isEqualTo(documentoGuardado.getIdDocumento());
        assertThat(nroDocumento).isEqualTo(documentoRespuesta.getNroDocumento());
    }
}