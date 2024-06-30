package com.c2.ClinicaOdontologica.Controller;

import com.c2.ClinicaOdontologica.entity.Paciente;
import com.c2.ClinicaOdontologica.exception.ResorceNotFoundException;
import com.c2.ClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPacientePorID(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.buscarPacientePorID(id));
    }
    @GetMapping("/todos")
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.listarTodos());
    }
    @PostMapping
    public  ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.registrarPaciente(paciente));
    }
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorID(paciente.getId());
        if(pacienteBuscado.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("paciente actualizado");
        }else{
            return ResponseEntity.badRequest().body("paciente no encontrado");
        }
    }
    @GetMapping("/buscar/{email}")
    public ResponseEntity<Paciente> buscarPacientePorCorreo(@PathVariable String correo){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorCorreo(correo);
        if(pacienteBuscado.isPresent()) {

            return ResponseEntity.ok(pacienteBuscado.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> elimninarPaciente(@PathVariable Long id) throws ResorceNotFoundException {
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorID(id);
        if(pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Eliminado con exito");
        }else{
            throw new ResorceNotFoundException("No existe ese ID en la bdd"+id);
            //return ResponseEntity.notFound().build();
        }

    }
}
