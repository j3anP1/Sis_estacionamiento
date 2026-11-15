package ec.edu.espe.sis_estacionamiento.controller;

import ec.edu.espe.sis_estacionamiento.dto.EspacioDTO;
import ec.edu.espe.sis_estacionamiento.service.IEspacioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/espacios")
public class EspacioController {
    @Autowired
    private IEspacioService espacioService;

    @GetMapping
    public ResponseEntity<List<EspacioDTO>> listar() {
        return ResponseEntity.ok(espacioService.listarEspacios());
    }

    // Proceso: Determinar disponibilidad de espacios
    @GetMapping("/disponibles")
    public ResponseEntity<List<EspacioDTO>> listarDisponibles() {
        return ResponseEntity.ok(espacioService.listarDisponibles());
    }

    // Proceso: Registrar espacios
    @PostMapping
    public ResponseEntity<EspacioDTO> crear(@RequestBody EspacioDTO espacioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(espacioService.crearEspacio(espacioDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspacioDTO> actualizar(@PathVariable Long id, @RequestBody EspacioDTO espacioDTO) {
        return ResponseEntity.ok(espacioService.actualizarEspacio(id, espacioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        espacioService.eliminarEspacio(id);
        return ResponseEntity.noContent().build();
    }
}
