package com.c2.ClinicaOdontologica.service;

import com.c2.ClinicaOdontologica.entity.Odontologo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @SpringBootTest
    public class OdontologoServiceTest {
        @Autowired
        private OdontologoService odontologoService;

        @Test
        @Order(1)
        public void guardarOdontologo(){
            Odontologo odontologo = new Odontologo("1432", "Juan", "Perez");
            odontologoService.registrarOdontologo(odontologo);
            assertNotNull(odontologo.getId());
        }

        @Test
        @Order(2)
        public void buscarOdontologoPorId(){
            Long id = 1L;
            Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
            assertTrue(odontologoBuscado.isPresent());
        }

        @Test
        @Order(3)
        public void buscarOdontologosTest(){
            List<Odontologo> odontologos = odontologoService.listarTodos();
            assertTrue(odontologos.size() > 0);
        }

        @Test
        @Order(4)
        public void actualizarOdontologo(){
            Long id = 1L;
            Optional<Odontologo> odontologoExistente = odontologoService.buscarPorId(id);
            if (odontologoExistente.isPresent()) {
                Odontologo odontologoActualizar = odontologoExistente.get();
                odontologoActualizar.setApellido("Lopez");
                odontologoService.actualizarOdontologo(odontologoActualizar);
                Optional<Odontologo> odontologoActualizado = odontologoService.buscarPorId(id);
                assertEquals("Lopez", odontologoActualizado.get().getApellido());
            } else {
                fail("El odontólogo no existe, no se puede actualizar.");
            }
        }

        @Test
        @Order(5)
        public void eliminarOdontologo(){
            Long idEliminar = 1L;
            odontologoService.eliminarOdontologo(idEliminar);
            Optional<Odontologo> odontologoEliminado = odontologoService.buscarPorId(idEliminar);
            assertFalse(odontologoEliminado.isPresent());
        }
    }

