package com.cibertec.step911.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "alertas")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alerta")
    private Integer idAlerta;

    @Column(name = "telefono_informante", length = 15)
    private String telefonoInformante;

    @Column(name = "dni_informante", length = 8)
    private String dniInformante;

    @Column(name = "nombre_informante", length = 100)
    private String nombreInformante;

    @Column(name = "descripcion_hechos", nullable = false, columnDefinition = "TEXT")
    private String descripcionHechos;

    @Column(name = "referencias_ubicacion", columnDefinition = "TEXT")
    private String referenciasUbicacion;

    @Column(name = "latitud_emergencia")
    private Double latitudEmergencia;

    @Column(name = "longitud_emergencia")
    private Double longitudEmergencia;

    @Column(name = "codigo_severidad", nullable = false, length = 20)
    private String codigoSeveridad;

    @Column(name = "estado_alerta", length = 20)
    private String estadoAlerta = "PENDIENTE";

    @Column(name = "fecha_hora_registro", insertable = false, updatable = false)
    private LocalDateTime fechaHoraRegistro;

    // Relación con el Operador que registra la alerta
    @ManyToOne
    @JoinColumn(name = "id_usuario_operador")
    private Usuario usuarioOperador;

    // Relación con la Patrulla asignada
    @ManyToOne
    @JoinColumn(name = "id_unidad_asignada")
    private UnidadPolicial unidadAsignada;

    // ==========================================
    // GETTERS Y SETTERS MANUALES
    // ==========================================
    
    public Integer getIdAlerta() { return idAlerta; }
    public void setIdAlerta(Integer idAlerta) { this.idAlerta = idAlerta; }

    public String getTelefonoInformante() { return telefonoInformante; }
    public void setTelefonoInformante(String telefonoInformante) { this.telefonoInformante = telefonoInformante; }

    public String getDniInformante() { return dniInformante; }
    public void setDniInformante(String dniInformante) { this.dniInformante = dniInformante; }

    public String getNombreInformante() { return nombreInformante; }
    public void setNombreInformante(String nombreInformante) { this.nombreInformante = nombreInformante; }

    public String getDescripcionHechos() { return descripcionHechos; }
    public void setDescripcionHechos(String descripcionHechos) { this.descripcionHechos = descripcionHechos; }

    public String getReferenciasUbicacion() { return referenciasUbicacion; }
    public void setReferenciasUbicacion(String referenciasUbicacion) { this.referenciasUbicacion = referenciasUbicacion; }

    public Double getLatitudEmergencia() { return latitudEmergencia; }
    public void setLatitudEmergencia(Double latitudEmergencia) { this.latitudEmergencia = latitudEmergencia; }

    public Double getLongitudEmergencia() { return longitudEmergencia; }
    public void setLongitudEmergencia(Double longitudEmergencia) { this.longitudEmergencia = longitudEmergencia; }

    public String getCodigoSeveridad() { return codigoSeveridad; }
    public void setCodigoSeveridad(String codigoSeveridad) { this.codigoSeveridad = codigoSeveridad; }

    public String getEstadoAlerta() { return estadoAlerta; }
    public void setEstadoAlerta(String estadoAlerta) { this.estadoAlerta = estadoAlerta; }

    public LocalDateTime getFechaHoraRegistro() { return fechaHoraRegistro; }
    public void setFechaHoraRegistro(LocalDateTime fechaHoraRegistro) { this.fechaHoraRegistro = fechaHoraRegistro; }

    public Usuario getUsuarioOperador() { return usuarioOperador; }
    public void setUsuarioOperador(Usuario usuarioOperador) { this.usuarioOperador = usuarioOperador; }

    public UnidadPolicial getUnidadAsignada() { return unidadAsignada; }
    public void setUnidadAsignada(UnidadPolicial unidadAsignada) { this.unidadAsignada = unidadAsignada; }
}