package ec.edu.espe.sis_estacionamiento.controller;

import ec.edu.espe.sis_estacionamiento.dto.RegistroDTO;
import ec.edu.espe.sis_estacionamiento.service.IRegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros")
public class RegistroController {
    @Autowired
    private IRegistroService registroService;

    @GetMapping
    public ResponseEntity<List<RegistroDTO>> listar() {
        return ResponseEntity.ok(registroService.traerRegistros());
    }

    // Proceso: Controlar ingreso
    @PostMapping
    public ResponseEntity<RegistroDTO> registrarIngreso(@RequestBody RegistroDTO registroDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(registroService.crearRegistro(registroDTO));
    }

    // Proceso: Controlar salida, calcular tiempo de permanencia y liberar espacio
    @PatchMapping("/{id}/salida")
    public ResponseEntity<RegistroDTO> registrarSalida(@PathVariable Long id) {
        return ResponseEntity.ok(registroService.registrarSalida(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroDTO> actualizar(@PathVariable Long id, @RequestBody RegistroDTO registroDTO) {
        return ResponseEntity.ok(registroService.actualizarRegistro(id, registroDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        registroService.eliminarRegistro(id);
        return ResponseEntity.noContent().build();
    }
}
