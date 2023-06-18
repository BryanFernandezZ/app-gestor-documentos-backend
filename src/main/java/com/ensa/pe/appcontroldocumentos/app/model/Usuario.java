package com.ensa.pe.appcontroldocumentos.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String nombre;
    @Column(length = 70)
    private String apellidos;
    @ManyToOne(fetch=FetchType.EAGER, optional = false)
    @JoinColumn(name = "tipo_identidad_id")
    private TipoIdentidad tipoIdentidad;
    @Column(length = 25)
    private String nroIdentidad;
    @Column(length = 9)
    private String telefono;
    @Column(length = 60)
    private String correo;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String avatar;
    @ManyToOne(fetch=FetchType.EAGER, optional = false)
    @JoinColumn(name = "area_id")
    private Area area;
    @ManyToOne(fetch=FetchType.EAGER, optional = false)
    @JoinColumn(name = "rol_id")
    private Rol rol;
    @Column(length = 50, unique = true)
    private String usuario;
    @Column(length = 60)
    private String contrasenia;
    private boolean isActive;
    @OneToMany(mappedBy = "usuarioAsignado")
    private List<Documento> documentos = new ArrayList<>();
}
