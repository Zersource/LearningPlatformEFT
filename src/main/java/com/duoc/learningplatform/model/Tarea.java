package com.duoc.learningplatform.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tareas")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String descripcion;

    @Column(name = "curso_id")
    private Long cursoId;

    @Column(name = "fecha_entrega")
    private String fechaEntrega;

    private String estado; // PENDIENTE, ENTREGADA, CALIFICADA

    // --- Constructores ---

    public Tarea() {}

    public Tarea(String titulo, String descripcion, Long cursoId, String fechaEntrega) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.cursoId = cursoId;
        this.fechaEntrega = fechaEntrega;
        this.estado = "PENDIENTE";
    }

    // --- Getters y Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Long getCursoId() { return cursoId; }
    public void setCursoId(Long cursoId) { this.cursoId = cursoId; }

    public String getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(String fechaEntrega) { this.fechaEntrega = fechaEntrega; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
