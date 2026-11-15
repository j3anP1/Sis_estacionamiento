package ec.edu.espe.sis_estacionamiento.service;

import ec.edu.espe.sis_estacionamiento.dto.VehiculoDTO;

import java.util.List;

public interface IVehiculoService {
    List<VehiculoDTO> listarVehiculos();
    VehiculoDTO crearVehiculo (Long id, VehiculoDTO vehiculoDTO);
    VehiculoDTO actualizarVehiculo (Long id, VehiculoDTO vehiculoDTO);
    void eliminarVehiculo (Long id);
}
