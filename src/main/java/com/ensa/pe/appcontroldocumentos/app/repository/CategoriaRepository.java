package com.ensa.pe.appcontroldocumentos.app.repository;

import com.ensa.pe.appcontroldocumentos.app.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
