package com.ensa.pe.appcontroldocumentos.app.security;

import com.ensa.pe.appcontroldocumentos.app.dto.res.RolResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.UsuarioResponseAuxDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.UsuarioResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.mapper.UsuarioMapper;
import com.ensa.pe.appcontroldocumentos.app.model.Rol;
import com.ensa.pe.appcontroldocumentos.app.model.Usuario;
import com.ensa.pe.appcontroldocumentos.app.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;
    private UsuarioMapper usuarioMapper;

    public UserDetailService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe"));

        UsuarioResponseAuxDTO usuarioResponseAuxDTO = usuarioMapper.mapearAAuxDTO(usuario);

        return new User(usuarioResponseAuxDTO.getUsuario(),
                usuarioResponseAuxDTO.getContrasenia(),
                mapearRoles(Arrays.asList(usuarioResponseAuxDTO.getRol())));
    }

    public Collection<? extends GrantedAuthority> mapearRoles(List<RolResponseDTO> roles) {
        return roles.stream().map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.getRol()))
                .collect(Collectors.toList());
    }
}
