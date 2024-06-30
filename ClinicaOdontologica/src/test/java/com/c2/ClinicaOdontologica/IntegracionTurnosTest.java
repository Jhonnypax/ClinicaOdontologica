    package com.c2.ClinicaOdontologica;

    import com.c2.ClinicaOdontologica.entity.Domicilio;
    import com.c2.ClinicaOdontologica.entity.Odontologo;
    import com.c2.ClinicaOdontologica.entity.Paciente;
    import com.c2.ClinicaOdontologica.entity.Turno;
    import com.c2.ClinicaOdontologica.service.OdontologoService;
    import com.c2.ClinicaOdontologica.service.PacienteService;
    import com.c2.ClinicaOdontologica.service.TurnoService;
    import org.junit.jupiter.api.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.http.MediaType;
    import org.springframework.test.web.servlet.MockMvc;
    import org.springframework.test.web.servlet.MvcResult;
    import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
    import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
    import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

    import java.time.LocalDate;

    import static org.junit.jupiter.api.Assertions.assertFalse;

    @SpringBootTest
    @AutoConfigureMockMvc(addFilters = false) //nececito desactivar la seguridad en la clase
    public class IntegracionTurnosTest {
        @Autowired
        private TurnoService turnoService;
        @Autowired
        private PacienteService pacienteService;
        @Autowired
        private OdontologoService odontologoService;
        @Autowired
        private MockMvc mockMvc;

        public void agregarDatosIniciales(){
            Paciente paciente= new Paciente("Ricardo","Guti","12345", LocalDate.of(2023,11,28),new Domicilio("Calle ",2,"La Esperanza","La Esperanza"),"RichGut@gmail.com");
            Odontologo odontologo= new Odontologo("123","Lola","Fugaz");
            Turno turno= new Turno(paciente,odontologo,LocalDate.of(2023,12,01));
            pacienteService.registrarPaciente(paciente);
            odontologoService.registrarOdontologo(odontologo);
            turnoService.guardarTurno(turno);
        }
        @Test
        public void listarTurnosTest() throws Exception{
            agregarDatosIniciales();
            MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
            assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
        }


    }
