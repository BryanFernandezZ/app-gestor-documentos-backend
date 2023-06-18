package com.ensa.pe.appcontroldocumentos.app;

import com.ensa.pe.appcontroldocumentos.app.model.*;
import com.ensa.pe.appcontroldocumentos.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class AppControlSeguimientoDocumentosApplication implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TipoIdentidadRepository tipoIdentidadRepository;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private DocumentoRepository documentoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Autowired
    private PasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(AppControlSeguimientoDocumentosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        //Roles
//        crearRoles();
////
////        //Areas
//        crearAreas();
////
////        //Tipo de Identidades
//        crearTipoIdentidades();
////
////        //Usuarios
////        crearUsuarios();
////
////        //Categorias
//        crearCategorias();
//
//        //Tipos de Documentos
//        crearTipoDocumentos();
//
//        //Documentos
//        crearDocumentos();
    }

    private void crearRoles() {
        List<Rol> roles = Arrays.asList(
                Rol.builder().nombre("Supervisor").build(),
                Rol.builder().nombre("Gestor").build()
        );

        roles.forEach(rolRepository::save);
    }

    private void crearAreas() {
        List<Area> areas = Arrays.asList(
                Area.builder().nombre("Operativa").build(),
                Area.builder().nombre("Administrativa").build()
        );

        areas.forEach(areaRepository::save);
    }

    private void crearTipoIdentidades() {
        List<TipoIdentidad> tipoIdentidades = Arrays.asList(
                TipoIdentidad.builder().nombre("DNI").build(),
                TipoIdentidad.builder().nombre("Pasaporte").build()
        );

        tipoIdentidades.forEach(tipoIdentidadRepository::save);
    }

    private void crearUsuarios() {
        List<Usuario> usuarios = Arrays.asList(
                Usuario.builder()
                        .nombre("Cristian")
                        .apellidos("Zapata Saenz")
                        .tipoIdentidad(
                                TipoIdentidad.builder().id(Long.valueOf(1)).build()
                        )
                        .nroIdentidad("45871236")
                        .telefono("978123468")
                        .correo("cristian@correo.com")
                        .avatar(getAvatar("temporal_profile_picture.jpg"))
                        .area(
                                Area.builder().id(Long.valueOf(2)).build()
                        )
                        .rol(
                                Rol.builder().id(Long.valueOf(1)).build()
                        )
                        .usuario("czapata")
                        .contrasenia(encoder.encode("Czapata$"))
                        .isActive(true)
                        .build()

//                Usuario.builder()
//                        .nombre("Brayan Robert")
//                        .apellidos("Fernandez Zabaleta")
//                        .tipoIdentidad(
//                                TipoIdentidad.builder().id(Long.valueOf(1)).build()
//                        )
//                        .nroIdentidad("75841246")
//                        .telefono("971138918")
//                        .correo("bryan@correo.com")
//                        .avatar(getAvatar("temporal_profile_picture.jpg"))
//                        .area(
//                                Area.builder().id(Long.valueOf(1)).build()
//                        )
//                        .rol(
//                                Rol.builder().id(Long.valueOf(2)).build()
//                        )
//                        .usuario("brayan123")
//                        .contrasenia(encoder.encode("Brayan123$"))
//                        .isActive(true)
//                        .build(),
//
//                Usuario.builder()
//                        .nombre("Juan Marcos")
//                        .apellidos("Garcia Mendez")
//                        .tipoIdentidad(
//                                TipoIdentidad.builder().id(Long.valueOf(1)).build()
//                        )
//                        .nroIdentidad("75841246")
//                        .telefono("971138918")
//                        .correo("bryan@correo.com")
//                        .avatar(getAvatar("temporal_profile_picture.jpg"))
//                        .area(
//                                Area.builder().id(Long.valueOf(1)).build()
//                        )
//                        .rol(
//                                Rol.builder().id(Long.valueOf(2)).build()
//                        )
//                        .usuario("juan123")
//                        .contrasenia(encoder.encode("Juan123$"))
//                        .isActive(true)
//                        .build()
        );

        usuarios.forEach(usuarioRepository::save);
    }

    private void crearCategorias() {
        List<Categoria> categoriasArray = Arrays.asList(
                Categoria.builder()
                        .nombre("Carta")
                        .build(),
                Categoria.builder()
                        .nombre("Acta")
                        .build(),
                Categoria.builder()
                        .nombre("Cedula")
                        .build()
        );

        categoriasArray.forEach(categoriaRepository::save);
    }

    private void crearTipoDocumentos() {
        List<TipoDocumento> tipoDocumentos = Arrays.asList(
                TipoDocumento.builder()
                        .nombre("Carta de contraste")
                        .categoria(
                                Categoria.builder()
                                        .idCategoria(Long.valueOf(1))
                                        .build()
                        )
                        .build(),
                TipoDocumento.builder()
                        .nombre("Carta de empresas contrastadoras")
                        .categoria(
                                Categoria.builder()
                                        .idCategoria(Long.valueOf(1))
                                        .build()
                        )
                        .build(),
                TipoDocumento.builder()
                        .nombre("Carta de Inicio de Reclamo")
                        .categoria(
                                Categoria.builder()
                                        .idCategoria(Long.valueOf(1))
                                        .build()
                        )
                        .build(),
                TipoDocumento.builder()
                        .nombre("Carta Informativa 079")
                        .categoria(
                                Categoria.builder()
                                        .idCategoria(Long.valueOf(1))
                                        .build()
                        )
                        .build(),
                TipoDocumento.builder()
                        .nombre("Carta Pre Control de Admisibilidad")
                        .categoria(
                                Categoria.builder()
                                        .idCategoria(Long.valueOf(1))
                                        .build()
                        )
                        .build(),
                TipoDocumento.builder()
                        .nombre("Cedula Resolucion de Reclamos")
                        .categoria(
                                Categoria.builder()
                                        .idCategoria(Long.valueOf(3))
                                        .build()
                        )
                        .build(),
                TipoDocumento.builder()
                        .nombre("Cedula Resolucion Suspendido")
                        .categoria(
                                Categoria.builder()
                                        .idCategoria(Long.valueOf(3))
                                        .build()
                        )
                        .build()
        );

        tipoDocumentoRepository.saveAll(tipoDocumentos);
    }

    private void crearDocumentos() {
        List<Documento> documentosArray = Arrays.asList(
                Documento.builder()
                        .tipoDocumento(
                                TipoDocumento.builder()
                                        .idTipoDocumento(Long.valueOf(1))
                                        .build()
                        )
                        .fechaDocumento(LocalDate.of(2023, Calendar.APRIL, 21)) //new Date(2023, Calendar.APRIL, 21)
                        .nroDocumento("GSC-EC-R67916-A-2022")
                        .remitente("ROMERO PERALTA, MARLENI AIDEE") //TODO: SUPONIENDO DE QUE EL REMITENTE ES EL USUARO DE LA RELACION ONE TO MANY
                        .direccionProcesal("Anexo LAS DELICIAS Nº POZO DE AGUA Localidad Caserio Puente Machuca")
                        .suministro("27311180")
                        .referencia("R67916-A-2022")
                        .descripcion("Descripcion del documento uno")
                        .usuarioAnterior(null)
                        .usuarioAsignado(
                                Usuario.builder()
                                        .id(Long.valueOf(1))
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
                        .build(),

                Documento.builder()
                        .tipoDocumento(
                                TipoDocumento.builder()
                                        .idTipoDocumento(Long.valueOf(3))
                                        .build()
                        )
                        .fechaDocumento(LocalDate.of(2023, Calendar.APRIL, 18))
                        .nroDocumento("GSC-CI-R40454-E-2022")
                        .remitente("Cabrera Valdivia, Efraín") //TODO: SUPONIENDO DE QUE EL REMITENTE ES EL USUARO DE LA RELACION ONE TO MANY
                        .direccionProcesal("Mz. A Lote S/N Caserio Chepito Bajo")
                        .suministro("38898992")
                        .referencia("R40454-E-2022")
                        .descripcion("Descripcion del documento dos")
                        .usuarioAnterior(null)
                        .usuarioAsignado(
                                Usuario.builder()
                                        .id(Long.valueOf(1))
                                        .build()
                        )
                        .archivoAdjunto(null)
                        .fechaCreacion(LocalDate.of(2023, Calendar.APRIL, 23))
                        .fechaDerivacion(null)
                        .fechaNotificacion(null)
                        .fechaFinalizacion(null)
                        .estado(true)
                        .creado(true)
                        .derivado(false)
                        .notificado(false)
                        .finalizado(false)
                        .build(),
                Documento.builder()
                        .tipoDocumento(
                                TipoDocumento.builder()
                                        .idTipoDocumento(Long.valueOf(3))
                                        .build()
                        )
                        .fechaDocumento(LocalDate.of(2023, Calendar.APRIL, 18))
                        .nroDocumento("GC-01-R40380-E-2022")
                        .remitente("Santisteban Llontop, Pedro Pablo") //TODO: SUPONIENDO DE QUE EL REMITENTE ES EL USUARO DE LA RELACION ONE TO MANY
                        .direccionProcesal("Direccion Procesal Two")
                        .suministro("37050168")
                        .referencia("R40380-E-2022")
                        .descripcion("Descripcion del documento tres")
                        .usuarioAnterior(null)
                        .usuarioAsignado(
                                Usuario.builder()
                                        .id(Long.valueOf(1))
                                        .build()
                        )
                        .archivoAdjunto(null)
                        .fechaCreacion(LocalDate.of(2023, Calendar.APRIL, 23))
                        .fechaDerivacion(null)
                        .fechaNotificacion(null)
                        .fechaFinalizacion(null)
                        .estado(true)
                        .creado(true)
                        .derivado(false)
                        .notificado(false)
                        .finalizado(false)
                        .build()
        );

        documentosArray.forEach(documentoRepository::save);
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
