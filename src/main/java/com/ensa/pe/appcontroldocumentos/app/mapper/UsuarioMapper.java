package com.ensa.pe.appcontroldocumentos.app.mapper;

import com.ensa.pe.appcontroldocumentos.app.dto.req.UsuarioRequestDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.UsuarioDocumentoResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.UsuarioResponseAuxDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.UsuarioResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.model.Usuario;
import com.ensa.pe.appcontroldocumentos.app.util.FileConverter;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    //TODO: DEPENDENCY INJECTION (13-23)
    private TipoIdentidadMapper tipoIdentidadMapper;
    private AreaMapper areaMapper;
    private RolMapper rolMapper;
    private FileConverter fileConverter;

    public UsuarioMapper(TipoIdentidadMapper tipoIdentidadMapper, AreaMapper areaMapper, RolMapper rolMapper, FileConverter fileConverter) {
        this.tipoIdentidadMapper = tipoIdentidadMapper;
        this.areaMapper = areaMapper;
        this.rolMapper = rolMapper;
        this.fileConverter = fileConverter;
    }

    public UsuarioResponseDTO mapearADTO(Usuario usuario) {
        return UsuarioResponseDTO.builder()
                .codigo(usuario.getId())
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .tipoIdentidad(tipoIdentidadMapper.mapearADTO(usuario.getTipoIdentidad()))
                .nroIdentidad(usuario.getNroIdentidad())
                .telefono(usuario.getTelefono())
                .correo(usuario.getCorreo())
                .avatar(fileConverter.convertirAByteArray(usuario.getAvatar()))
                .area(areaMapper.mapearADTO(usuario.getArea()))
                .rol(rolMapper.mapearADTO(usuario.getRol()))
                .usuario(usuario.getUsuario())
                .isActive(usuario.isActive())
                .build();
    }

    public Usuario mapearAEntidad(UsuarioRequestDTO usuarioRequestDTO) {
        return Usuario.builder()
                .id(usuarioRequestDTO.getCodigo())
                .nombre(usuarioRequestDTO.getNombre())
                .apellidos(usuarioRequestDTO.getApellidos())
                .tipoIdentidad(tipoIdentidadMapper.mapearAEntidad(usuarioRequestDTO.getTipoIdentidad()))
                .nroIdentidad(usuarioRequestDTO.getNroIdentidad())
                .telefono(usuarioRequestDTO.getTelefono())
                .correo(usuarioRequestDTO.getCorreo())
                .avatar(fileConverter.convertirAMultipartFile(usuarioRequestDTO.getAvatar()))
                .area(areaMapper.mapearAEntidad(usuarioRequestDTO.getArea()))
                .rol(rolMapper.mapearAEntidad(usuarioRequestDTO.getRol()))
                .usuario(usuarioRequestDTO.getUsuario())
                .contrasenia(usuarioRequestDTO.getContrasenia())
                .isActive(usuarioRequestDTO.isActive())
                .build();
    }

    public UsuarioResponseAuxDTO mapearAAuxDTO(Usuario usuario) {
        return UsuarioResponseAuxDTO.builder()
                .codigo(usuario.getId())
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .tipoIdentidad(tipoIdentidadMapper.mapearADTO(usuario.getTipoIdentidad()))
                .nroIdentidad(usuario.getNroIdentidad())
                .telefono(usuario.getTelefono())
                .correo(usuario.getCorreo())
                .avatar(fileConverter.convertirAByteArray(usuario.getAvatar()))
                .area(areaMapper.mapearADTO(usuario.getArea()))
                .rol(rolMapper.mapearADTO(usuario.getRol()))
                .usuario(usuario.getUsuario())
                .contrasenia(usuario.getContrasenia())
                .isActive(usuario.isActive())
                .build();
    }

    public UsuarioDocumentoResponseDTO mapearAUsuarioDocumentoDTO(Usuario usuario) {
        return UsuarioDocumentoResponseDTO.builder()
                .codigo(usuario.getId())
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .build();
    }

    public Usuario mapearAUsuarioDocumentoEntidad(UsuarioRequestDTO usuarioRequestDTO){
        return Usuario.builder()
                .id(usuarioRequestDTO.getCodigo())
                .nombre(usuarioRequestDTO.getNombre())
                .apellidos(usuarioRequestDTO.getApellidos())
                .build();
    }
}
