package com.ensa.pe.appcontroldocumentos.app.controller;

import com.ensa.pe.appcontroldocumentos.app.dto.req.LoginRequestDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.LoginResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.exceptions.NotFoundException;
import com.ensa.pe.appcontroldocumentos.app.mapper.UsuarioMapper;
import com.ensa.pe.appcontroldocumentos.app.model.Usuario;
import com.ensa.pe.appcontroldocumentos.app.repository.UsuarioRepository;
import com.ensa.pe.appcontroldocumentos.app.security.jwt.JwtAuthenticationProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder encoder;
    private UsuarioMapper usuarioMapper;

    public AuthController(AuthenticationManager authenticationManager, JwtAuthenticationProvider jwtAuthenticationProvider, UsuarioRepository usuarioRepository, PasswordEncoder encoder, UsuarioMapper usuarioMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
        this.usuarioMapper = usuarioMapper;
    }

    @RequestMapping(path = "/iniciar-sesion", method = RequestMethod.POST, consumes = {APPLICATION_JSON_VALUE},
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> iniciarSesion(@RequestBody LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDTO.getUsername(), loginRequestDTO.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtAuthenticationProvider.generarToken(authentication);

        LoginResponseDTO loginResponseDto = LoginResponseDTO.builder()
                .tipoToken("Bearer")
                .token(token)
                .timestamp(LocalDateTime.now().toString())
                .build();

        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }

    @RequestMapping(path = "/verUsuario", method = RequestMethod.GET, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> obtenerUsuarioDelJwt(@RequestParam(value = "token", defaultValue = "", required = true) String token) {
        String username = jwtAuthenticationProvider.obtenerUsuarioDelJwt(token);
        Usuario usuario = usuarioRepository.findByUsuario(username)
                .orElseThrow(() -> new NotFoundException("Este usuario no existe"));
        return new ResponseEntity<>(usuarioMapper.mapearADTO(usuario), HttpStatus.OK);
    }
}
