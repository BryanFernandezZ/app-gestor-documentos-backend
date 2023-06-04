package com.ensa.pe.appcontroldocumentos.app.repository;

import com.ensa.pe.appcontroldocumentos.app.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuario(String usuario);

    @Query(value = "SELECT * FROM usuarios u WHERE u.area_id LIKE :areaId", nativeQuery = true)
    List<Usuario> obtenerUsuariosDeArea(@Param("areaId") Long areaId);

    @Query(value = "SELECT * FROM usuarios u WHERE concat(u.nombre, ' ', u.apellidos) LIKE :nombreApellidoUsuario",
            nativeQuery = true)
    Optional<Usuario> obtenerUsuarioPorNombreYApellido(@Param("nombreApellidoUsuario") String nombreApellidoUsuario);
}