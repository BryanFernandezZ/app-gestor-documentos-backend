package com.ensa.pe.appcontroldocumentos.app.controller;

import com.ensa.pe.appcontroldocumentos.app.dto.AsignarUsuarioDocumentoDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.req.DocumentoRequestDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.DocumentoResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.service.DocumentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/documento")
public class DocumentoController {

    private DocumentoService documentoService;

    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @RequestMapping(path = "/listar", method = RequestMethod.GET)
    public ResponseEntity<?> obtenerDocumentos() {
        List<DocumentoResponseDTO> documentosResponse = documentoService.obtenerDocumentos();
        return ResponseEntity.ok(documentosResponse);
    }

    @RequestMapping(path = "/listarPorUsuario/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> obtenerDocumentos(@PathVariable Long id) {
        if (id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        List<DocumentoResponseDTO> documentosResponse = documentoService.obtenerDocumentosPorUsuario(id);
        return ResponseEntity.ok(documentosResponse);
    }

    @RequestMapping(path = "/ver/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> verDocumento(@PathVariable Long id) {
        if (id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        DocumentoResponseDTO documentoResponse = documentoService.verDocumento(id);
        return ResponseEntity.ok(documentoResponse);
    }

    @RequestMapping(path = "/guardar", method = RequestMethod.POST)
    public ResponseEntity<?> guardarDocument(@RequestBody DocumentoRequestDTO documentoRequestDTO) {
        if (documentoRequestDTO == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        documentoRequestDTO.setFechaCreacion(LocalDate.now());
        documentoRequestDTO.setEstado(true);
        documentoRequestDTO.setCreado(true);
        documentoRequestDTO.setDerivado(false);
        documentoRequestDTO.setNotificado(false);
        documentoRequestDTO.setFinalizado(false);
        documentoService.crearDocumento(documentoRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/actualizar", method = RequestMethod.PUT)
    public ResponseEntity<?> actualizarDocumento(@RequestBody DocumentoRequestDTO documentoRequestDTO) {
        if (documentoRequestDTO == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        documentoService.actualizarDocumento(documentoRequestDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(path = "/actualizarArchivoAdjunto", method = RequestMethod.PUT)
    public ResponseEntity<?> actualizarDocumento(
            @RequestParam("codigo") Long codigo,
            @RequestParam("archivoAdjunto") MultipartFile archivoAdjunto
    ) {
        if(codigo == null || archivoAdjunto == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        documentoService.actualizarArchivoAdjunto(codigo, archivoAdjunto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(path = "/eliminar/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> eliminarDocumento(@PathVariable Long id) {
        if (id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        documentoService.eliminarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/derivar", method = RequestMethod.PUT)
    public ResponseEntity<?> derivarDocumento(@RequestBody AsignarUsuarioDocumentoDTO asignarUsuarioDocumentoDTO) {
        if (asignarUsuarioDocumentoDTO == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        documentoService.derivarDocumento(asignarUsuarioDocumentoDTO.getIdDocumento(),
                asignarUsuarioDocumentoDTO.getIdUsuarioAsignado());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/notificar", method = RequestMethod.PUT)
    public ResponseEntity<?> notificarDocumento(
            @RequestParam("idDocumento") Long idDocumento,
            @RequestParam("usuarioAnterior") String usuarioAnterior,
            @RequestParam("archivoAdjunto") MultipartFile archivoAdjunto
    ) {
        if (idDocumento == null || usuarioAnterior == null || archivoAdjunto == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        documentoService.notificarDocumento(idDocumento, usuarioAnterior, archivoAdjunto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/finalizar/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> finalizarDocumento(@PathVariable Long id) {
        if (id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        documentoService.finalizarDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
