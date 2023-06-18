package com.ensa.pe.appcontroldocumentos.app.service;

import com.ensa.pe.appcontroldocumentos.app.dto.req.DocumentoRequestDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.DocumentoResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.exceptions.InternalServerException;
import com.ensa.pe.appcontroldocumentos.app.exceptions.NotFoundException;
import com.ensa.pe.appcontroldocumentos.app.mapper.DocumentoMapper;
import com.ensa.pe.appcontroldocumentos.app.mapper.TipoDocumentoMapper;
import com.ensa.pe.appcontroldocumentos.app.model.Documento;
import com.ensa.pe.appcontroldocumentos.app.model.Usuario;
import com.ensa.pe.appcontroldocumentos.app.repository.DocumentoRepository;
import com.ensa.pe.appcontroldocumentos.app.repository.UsuarioRepository;
import com.ensa.pe.appcontroldocumentos.app.util.FileConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentoServiceImp implements DocumentoService {

    private DocumentoRepository documentoRepository;
    private UsuarioRepository usuarioRepository;
    private DocumentoMapper documentoMapper;
    private TipoDocumentoMapper tipoDocumentoMapper;
    private FileConverter fileConverter;

    public DocumentoServiceImp(DocumentoRepository repository, UsuarioRepository usuarioRepository, DocumentoMapper documentoMapper, TipoDocumentoMapper tipoDocumentoMapper, FileConverter fileConverter) {
        this.documentoRepository = repository;
        this.usuarioRepository = usuarioRepository;
        this.documentoMapper = documentoMapper;
        this.tipoDocumentoMapper = tipoDocumentoMapper;
        this.fileConverter = fileConverter;
    }

    @Override
    public List<DocumentoResponseDTO> obtenerDocumentos() {
        List<Documento> documentos = documentoRepository.findAll();
        List<DocumentoResponseDTO> documentosDto = documentos.stream().map(documentoMapper::mapearADTO).collect(Collectors.toList());
        return documentosDto;
    }

    @Override
    public List<DocumentoResponseDTO> obtenerDocumentosPorUsuario(Long codigo) {
        List<Documento> documentos = documentoRepository.obtenerDocumentosPorUsuario(codigo)
                .orElseThrow(() -> new NotFoundException("Este usuario no tiene documentos asignados"));
        List<DocumentoResponseDTO> documentosDto = documentos.stream().map(documentoMapper::mapearADTO).collect(Collectors.toList());
        return documentosDto.stream().filter(d -> !d.isFinalizado()).collect(Collectors.toList());
    }

    @Override
    public DocumentoResponseDTO verDocumento(Long id) {
        Documento documento = documentoRepository.findById(id).orElseThrow(() -> new NotFoundException("El documento no existe"));
        DocumentoResponseDTO documentoDto = documentoMapper.mapearADTO(documento);
        return documentoDto;
    }

    @Override
    public void crearDocumento(DocumentoRequestDTO documentoRequestDTO) {

        Documento documentoDb = documentoRepository.findByNroDocumento(documentoRequestDTO.getNroDocumento())
                .orElse(null);

        if (documentoDb != null)
            throw new InternalServerException("Ya existe un documento registrado con el numero de documento " +
                    documentoRequestDTO.getNroDocumento());

        Documento documento = documentoMapper.mapearAEntidad(documentoRequestDTO);
        documentoRepository.save(documento);
    }

    @Override
    public void actualizarDocumento(DocumentoRequestDTO documentoRequestDTO) {
        Documento documento = documentoRepository.findById(documentoRequestDTO.getCodigo())
                .orElseThrow(() -> new NotFoundException("El documento no existe"));
//        documento.setTipoDocumento(tipoDocumentoMapper.mapearAEntidad(documentoRequestDTO.getDocumento()));
//        documento.setFechaDocumento(documento.getFechaDocumento());
//        documento.setNroDocumento(documentoRequestDTO.getNroDocumento());
//        documento.setRemitente(documentoRequestDTO.getRemitente());
//        documento.setDireccionProcesal(documentoRequestDTO.getDireccionProcesal());
//        documento.setSuministro(documentoRequestDTO.getSuministro());
//        documento.setReferencia(documentoRequestDTO.getReferencia());
//        documento.setDescripcion(documentoRequestDTO.getDescripcion());
//        documentoRepository.saveAndFlush(documento);

        Documento documentoGuardar = documentoMapper.mapearAEntidad(documentoRequestDTO);
        documentoRepository.saveAndFlush(documentoGuardar);
    }

    @Override
    public void eliminarDocumento(Long id) {
        Documento documento = documentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Este documento no existe"));
        documento.setEstado(false);
        documentoRepository.saveAndFlush(documento);
    }

    @Override
    public void actualizarArchivoAdjunto(Long id, MultipartFile archivoAdjunto) {
        Documento documento = documentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El documento no existe"));
        documento.setArchivoAdjunto(fileConverter.convertirAMultipartFile(archivoAdjunto));
        documentoRepository.saveAndFlush(documento);
    }

    @Override
    public void derivarDocumento(Long idDocumento, Long idUsuarioAsignado) {
        Documento documento = documentoRepository.findById(idDocumento)
                .orElseThrow(() -> new NotFoundException("El documento no existe"));

        documento.setUsuarioAnterior(
                documento.getUsuarioAsignado().getNombre() + " " + documento.getUsuarioAsignado().getApellidos());
        documento.setUsuarioAsignado(
                Usuario.builder()
                        .id(idUsuarioAsignado)
                        .build()
        );

        documento.setFechaDerivacion(LocalDate.now());
        documento.setDerivado(true);

        documentoRepository.saveAndFlush(documento);
    }

    @Override
    public void notificarDocumento(Long idDocumento, String nombreApellidoUsuario, MultipartFile file) {
        Usuario usuario = usuarioRepository.obtenerUsuarioPorNombreYApellido(nombreApellidoUsuario)
                .orElseThrow(() -> new NotFoundException("El usuario no existe"));

        Documento documento = documentoRepository.findById(idDocumento)
                .orElseThrow(() -> new NotFoundException("El documento no existe"));

        String usuarioAsignado = documento.getUsuarioAsignado().getNombre() + documento.getUsuarioAsignado()
                .getApellidos();

        documento.setArchivoAdjunto(fileConverter.convertirAMultipartFile(file));

        //TODO: ASIGNANDO NUEVO USUARIO A PARTIR DE LA BUSQUEDA POR NOMBRE Y APELLIDO HECHA ANTERIORMENTE
        documento.setUsuarioAsignado(usuario);

        //TODO: ASIGNANDO EL USUARIO ANTERIOR A PARTIR DEL USUARIO ASIGNADO ANTERIORMENTE
        documento.setUsuarioAnterior(usuarioAsignado);

        documento.setFechaNotificacion(LocalDate.now());
        documento.setNotificado(true);

        documentoRepository.saveAndFlush(documento);
    }

    @Override
    public void finalizarDocumento(Long idDocumento) {
        Documento documento = documentoRepository.findById(idDocumento)
                .orElseThrow(() -> new NotFoundException("El documento no existe"));

        documento.setFinalizado(true);
        documento.setFechaFinalizacion(LocalDate.now());

        documentoRepository.saveAndFlush(documento);
    }

    @Override
    public int obtenerCantidadDocumentos() {
        List<Documento> documentos = documentoRepository.findAll();
        int totalDocumentos = documentos.size();
        return totalDocumentos;
    }
}
