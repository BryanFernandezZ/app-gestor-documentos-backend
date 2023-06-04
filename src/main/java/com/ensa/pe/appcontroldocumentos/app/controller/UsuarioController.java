package com.ensa.pe.appcontroldocumentos.app.controller;

import com.ensa.pe.appcontroldocumentos.app.dto.req.AreaRequestDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.req.RolRequestDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.req.TipoIdentidadRequestDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.req.UsuarioRequestDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.UsuarioResponseAuxDTO;
import com.ensa.pe.appcontroldocumentos.app.dto.res.UsuarioResponseDTO;
import com.ensa.pe.appcontroldocumentos.app.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/usuario")
public class UsuarioController {

    //TODO: Dependency Injection
    private UsuarioService usuarioService;
    private PasswordEncoder encoder;

    public UsuarioController(UsuarioService usuarioService, PasswordEncoder encoder) {
        this.usuarioService = usuarioService;
        this.encoder = encoder;
    }

    @RequestMapping(path = "/listar", method = RequestMethod.GET)
    public ResponseEntity<?> obtenerUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioService.obtenerUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @RequestMapping(path = "/ver/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> verUsuario(@PathVariable Long id) {
        UsuarioResponseDTO usuario = usuarioService.verUsuario(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @RequestMapping(path = "/verAux/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> verUsuarioAux(@PathVariable Long id) {
        UsuarioResponseAuxDTO usuario = usuarioService.verUsuarioAux(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @RequestMapping(path = "/guardar", method = RequestMethod.POST)
    public ResponseEntity<?> guardarUsuario(
            @RequestParam("nombre") String nombre,
            @RequestParam("apellidos") String apellidos,
            @RequestParam("tipoIdentidad") String tipoIdentidad,
            @RequestParam("nroIdentidad") String nroIdentidad,
            @RequestParam("telefono") String telefono,
            @RequestParam("correo") String correo,
            @RequestParam("avatar") MultipartFile avatar,
            @RequestParam("area") String area,
            @RequestParam("rol") String rol,
            @RequestParam("usuario") String usuario,
            @RequestParam("contrasenia") String contrasenia
    ) {
        if (nombre == null || apellidos == null || tipoIdentidad == null || nroIdentidad == null || telefono == null ||
                correo == null || avatar.isEmpty() || area == null || rol == null || usuario == null || contrasenia == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        TipoIdentidadRequestDTO tipoIdentidadRequestDTO = TipoIdentidadRequestDTO.builder()
                .tipoIdentidad(tipoIdentidad).build();
        tipoIdentidadRequestDTO.setCodigo(tipoIdentidad.equals("DNI") ? Long.valueOf(1) : Long.valueOf(2));

        AreaRequestDTO areaRequestDTO = AreaRequestDTO.builder().area(area).build();
        areaRequestDTO.setCodigo(area.equals("Operativa") ? Long.valueOf(1) : Long.valueOf(2));

        RolRequestDTO rolRequestDTO = RolRequestDTO.builder().rol(rol).build();
        rolRequestDTO.setCodigo(rol.equals("Supervisor") ? Long.valueOf(1) : Long.valueOf(2));

        UsuarioRequestDTO usuarioRequestDTO = UsuarioRequestDTO.builder()
                .nombre(nombre).apellidos(apellidos).tipoIdentidad(tipoIdentidadRequestDTO).nroIdentidad(nroIdentidad)
                .telefono(telefono).correo(correo).avatar(avatar).area(areaRequestDTO).rol(rolRequestDTO)
                .usuario(usuario).contrasenia(encoder.encode(contrasenia)).isActive(true)
                .build();

        usuarioService.guardarUsuario(usuarioRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/actualizar", method = RequestMethod.PUT)
    public ResponseEntity<?> actualizarUsuario(
            @RequestParam("codigo") Long codigo,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellidos") String apellidos,
            @RequestParam("tipoIdentidad") String tipoIdentidad,
            @RequestParam("nroIdentidad") String nroIdentidad,
            @RequestParam("telefono") String telefono,
            @RequestParam("correo") String correo,
            @RequestParam("avatar") MultipartFile avatar,
            @RequestParam("area") String area,
            @RequestParam("rol") String rol,
            @RequestParam("usuario") String usuario,
            @RequestParam("contrasenia") String contrasenia,
            @RequestParam("active") String active
    ) {
        if (codigo == null || nombre == null || apellidos == null || tipoIdentidad == null || nroIdentidad == null ||
                telefono == null || correo == null || avatar.isEmpty() || area == null || rol == null ||
                usuario == null || contrasenia == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        TipoIdentidadRequestDTO tipoIdentidadRequestDTO = TipoIdentidadRequestDTO.builder()
                .tipoIdentidad(tipoIdentidad).build();
        tipoIdentidadRequestDTO.setCodigo(tipoIdentidad.equals("DNI") ? Long.valueOf(1) : Long.valueOf(2));

        AreaRequestDTO areaRequestDTO = AreaRequestDTO.builder().area(area).build();
        areaRequestDTO.setCodigo(area.equals("Operativa") ? Long.valueOf(1) : Long.valueOf(2));

        RolRequestDTO rolRequestDTO = RolRequestDTO.builder().rol(rol).build();
        rolRequestDTO.setCodigo(rol.equals("Supervisor") ? Long.valueOf(1) : Long.valueOf(2));

        UsuarioRequestDTO usuarioRequestDTO = UsuarioRequestDTO.builder()
                .codigo(codigo).nombre(nombre).apellidos(apellidos).tipoIdentidad(tipoIdentidadRequestDTO)
                .nroIdentidad(nroIdentidad).telefono(telefono).correo(correo).avatar(avatar).area(areaRequestDTO)
                .rol(rolRequestDTO).usuario(usuario).contrasenia(encoder.encode(contrasenia)).isActive(Boolean.parseBoolean(active))
                .build();

        usuarioService.actualizarUsuario(usuarioRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/eliminar/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(path = "/usuariosArea/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> obtenerUsuariosDeArea(@PathVariable Long id) {
        if(id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        List<UsuarioResponseAuxDTO> response = usuarioService.obtenerUsuariosDeArea(id);
        return ResponseEntity.ok(response);
    }
}
