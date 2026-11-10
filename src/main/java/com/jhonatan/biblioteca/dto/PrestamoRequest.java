package com.jhonatan.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class PrestamoRequest {
    @NotBlank(message = "El nombre de la persona es obligatorio")
    private String nombrePersona;
    @NotNull(message = "La fecha del prestamo es obligatoria")
    private LocalDate fechaPrestamo;
    @NotNull(message = "La fecha de devolucion es obligatoria")
    private LocalDate fechaDevolucion;
    @NotNull(message = "El ID del libro es obligatorio")
    private Long libroId;

    public PrestamoRequest() {
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }
}
