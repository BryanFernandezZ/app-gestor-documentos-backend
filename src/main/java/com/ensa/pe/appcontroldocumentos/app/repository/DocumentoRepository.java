package com.ensa.pe.appcontroldocumentos.app.repository;

import com.ensa.pe.appcontroldocumentos.app.model.Documento;
import com.ensa.pe.appcontroldocumentos.app.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    @Query(value = "SELECT * FROM documentos d where d.id_usuario LIKE :idusuario", nativeQuery = true)
    Optional<List<Documento>> obtenerDocumentosPorUsuario(@Param("idusuario") Long idusuario);

    Optional<Documento> findByNroDocumento(String nroDocumento);
}
