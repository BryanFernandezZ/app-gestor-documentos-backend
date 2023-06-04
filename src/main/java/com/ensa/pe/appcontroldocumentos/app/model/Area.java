package com.ensa.pe.appcontroldocumentos.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "areas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 40)
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "area")
    private List<Usuario> usuarios = new ArrayList<>();
}
