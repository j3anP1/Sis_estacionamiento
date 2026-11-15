package ec.edu.espe.sis_estacionamiento.controller;

import ec.edu.espe.sis_estacionamiento.dto.VehiculoDTO;
import ec.edu.espe.sis_estacionamiento.service.IVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
    @Autowired
    private IVehiculoService vehiculoService;

    @GetMapping
    public ResponseEntity<List<VehiculoDTO>> listar() {
        return ResponseEntity.ok(vehiculoService.listarVehiculos());
    }

    // Asocia el vehículo al usuario propietario mediante usuarioId
    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<VehiculoDTO> crear(@PathVariable Long usuarioId, @RequestBody VehiculoDTO vehiculoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoService.crearVehiculo(usuarioId, vehiculoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculoDTO> actualizar(@PathVariable Long id, @RequestBody VehiculoDTO vehiculoDTO) {
        return ResponseEntity.ok(vehiculoService.actualizarVehiculo(id, vehiculoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        vehiculoService.eliminarVehiculo(id);
        return ResponseEntity.noContent().build();
    }
}
