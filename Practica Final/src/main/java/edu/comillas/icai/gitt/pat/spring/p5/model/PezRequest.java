package edu.comillas.icai.gitt.pat.spring.p5.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PezRequest(
        @NotBlank
        String tipo,
        @NotBlank
        String color,
        @NotBlank
        String sexo,
        @NotBlank
        String correo
) {}