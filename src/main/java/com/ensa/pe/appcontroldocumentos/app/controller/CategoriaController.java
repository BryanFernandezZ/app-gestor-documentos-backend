package com.ensa.pe.appcontroldocumentos.app.controller;

import com.ensa.pe.appcontroldocumentos.app.dto.res.CategoriaResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/categoria")
public class CategoriaController {

    private CategoriaService categoriaService;

    //TODO: DEPENDENCY INJECTION
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @RequestMapping(path = "/listar", method = RequestMethod.GET)
    public ResponseEntity<?> obtenerCategorias(){
        List<CategoriaResponseDTO> response = categoriaService.obtenerCategorias();
        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "/ver/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> verCategoria(@PathVariable Long id){
        if(id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        CategoriaResponseDTO response = categoriaService.verCategoria(id);
        return ResponseEntity.ok(response);
    }
}
