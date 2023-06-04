package com.ensa.pe.appcontroldocumentos.app.service;

import com.ensa.pe.appcontroldocumentos.app.dto.req.UsuarioRequestDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.UsuarioResponseAuxDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {
    List<UsuarioResponseDTO> obtenerUsuarios();
    UsuarioResponseDTO verUsuario(Long id);
    void guardarUsuario(UsuarioRequestDTO usuarioRequestDTO);
    void actualizarUsuario(UsuarioRequestDTO usuarioRequestDTO);
    void eliminarUsuario(Long id);

    UsuarioResponseAuxDTO verUsuarioAux(Long id);

    List<UsuarioResponseAuxDTO> obtenerUsuariosDeArea(Long areaId);
}
