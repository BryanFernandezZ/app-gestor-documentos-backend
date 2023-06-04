package com.ensa.pe.appcontroldocumentos.app.controller;

import com.ensa.pe.appcontroldocumentos.app.dto.req.TipoDocumentoRequestDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.TipoDocumentoResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.service.TipoDocumentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/tipoDocumento")
public class TipoDocumentoController {

    private TipoDocumentoService tipoDocumentoService;

    //TODO: DEPENDENCY INJECTION
    public TipoDocumentoController(TipoDocumentoService tipoDocumentoService) {
        this.tipoDocumentoService = tipoDocumentoService;
    }

    @RequestMapping(path = "/listar", method = RequestMethod.GET)
    public ResponseEntity<?> obtenerTipoDocumentos(){
        List<TipoDocumentoResponseDTO> tipoDocumentoResponse = tipoDocumentoService.obtenerTiposDocumento();
        return ResponseEntity.ok(tipoDocumentoResponse);
    }

    @RequestMapping(path = "/guardar", method = RequestMethod.POST)
    public ResponseEntity<?> guardarTipoDocumento(@RequestBody TipoDocumentoRequestDTO tipoDocumentoRequestDTO) {
        if(tipoDocumentoRequestDTO == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        tipoDocumentoService.guardarTipoDocumento(tipoDocumentoRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
