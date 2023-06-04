package com.ensa.pe.appcontroldocumentos.app.service;

import com.ensa.pe.appcontroldocumentos.app.dto.req.UsuarioRequestDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.UsuarioResponseAuxDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.UsuarioResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.exceptions.NotFoundException;
import com.ensa.pe.appcontroldocumentos.app.mapper.UsuarioMapper;
import com.ensa.pe.appcontroldocumentos.app.model.Usuario;
import com.ensa.pe.appcontroldocumentos.app.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImp implements UsuarioService {

    //Dependency Injection
    private UsuarioRepository usuarioRepository;
    private UsuarioMapper usuarioMapper;

    public UsuarioServiceImp(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public List<UsuarioResponseDTO> obtenerUsuarios() {
        //Desde base de datos
        List<Usuario> usuariosdb = usuarioRepository.findAll();
        List<UsuarioResponseDTO> usuarios = usuariosdb.stream().map(usuarioMapper::mapearADTO).collect(Collectors.toList());
        return usuarios;
    }

    @Override
    public UsuarioResponseDTO verUsuario(Long id) {
        Usuario usuariodb = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        UsuarioResponseDTO usuario = usuarioMapper.mapearADTO(usuariodb);
        return usuario;
    }

    @Override
    public void guardarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = usuarioMapper.mapearAEntidad(usuarioRequestDTO);
        usuarioRepository.save(usuario);
    }

    @Override
    public void actualizarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = usuarioMapper.mapearAEntidad(usuarioRequestDTO);
        usuarioRepository.saveAndFlush(usuario);
    }

    @Override
    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        usuario.setActive(false);
        usuarioRepository.saveAndFlush(usuario);
    }

    @Override
    public UsuarioResponseAuxDTO verUsuarioAux(Long id) {
        Usuario usuariodb = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        UsuarioResponseAuxDTO usuario = usuarioMapper.mapearAAuxDTO(usuariodb);
        return usuario;
    }

    @Override
    public List<UsuarioResponseAuxDTO> obtenerUsuariosDeArea(Long areaId) {
        List<Usuario> usuarios = usuarioRepository.obtenerUsuariosDeArea(areaId);
        List<UsuarioResponseAuxDTO> response = usuarios.stream().map(usuarioMapper::mapearAAuxDTO).collect(Collectors.toList());
        return response.stream().map(usuario -> UsuarioResponseAuxDTO.builder()
                        .codigo(usuario.getCodigo())
                        .nombre(usuario.getNombre())
                        .apellidos(usuario.getApellidos())
                        .area(usuario.getArea())
                        .build())
                .collect(Collectors.toList());
    }
}
