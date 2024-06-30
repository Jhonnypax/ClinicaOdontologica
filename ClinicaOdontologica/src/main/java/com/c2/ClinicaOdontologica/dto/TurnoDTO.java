package com.c2.ClinicaOdontologica.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TurnoDTO {
    private Long id;
    private LocalDate fechaTurno;
    private Long pacienteId;
    private Long odontologoId;
}
