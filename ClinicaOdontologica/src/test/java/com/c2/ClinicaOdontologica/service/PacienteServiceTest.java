package com.c2.ClinicaOdontologica.service;

import com.c2.ClinicaOdontologica.entity.Domicilio;
import com.c2.ClinicaOdontologica.entity.Paciente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPaciente(){
        Paciente paciente= new Paciente("Juan","Paez","11111", LocalDate.of(2023,11,28),new Domicilio("Calle ",1,"La Esperanza","La Esperanza"),"jpm@gmail.com");
        pacienteService.registrarPaciente(paciente);
        assertEquals(1L,paciente.getId());
    }
    @Test
    @Order(2)
    public void buscarPacientePorId(){
        Long id=1L;
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorID(id);
        assertNotNull(pacienteBuscado);
    }
    @Test
    @Order(3)
    public void buscarPacientesTest(){
        List<Paciente> pacientes= pacienteService.listarTodos();
        assertEquals(1,pacientes.size());
    }
    @Test
    @Order(4)
    public void actualizarPaciente(){
        Paciente pacienteActualizar= new Paciente(1L,"Abel","Lima","32452", LocalDate.of(2023,11,28),new Domicilio("Calle ",1,"La salta","La salta"),"Abelam@gmail.com");
        pacienteService.actualizarPaciente(pacienteActualizar);
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorID(1L);
        assertEquals("Abel",pacienteBuscado.get().getNombre());
    }
    @Test
    @Order(5)
    public void eliminarPaciente(){
        Long idEliminar= 1L;
        pacienteService.eliminarPaciente(1L);
        Optional<Paciente> pacienteEliminado= pacienteService.buscarPacientePorID(1L);
        assertFalse(pacienteEliminado.isPresent());

    }
}
