package ec.edu.espe.sis_estacionamiento.service;

import ec.edu.espe.sis_estacionamiento.dto.VehiculoDTO;
import ec.edu.espe.sis_estacionamiento.exception.DuplicateResourceException;
import ec.edu.espe.sis_estacionamiento.exception.NotFoundException;
import ec.edu.espe.sis_estacionamiento.mapper.Mapper;
import ec.edu.espe.sis_estacionamiento.model.Usuario;
import ec.edu.espe.sis_estacionamiento.model.Vehiculo;
import ec.edu.espe.sis_estacionamiento.repository.UsuarioRepository;
import ec.edu.espe.sis_estacionamiento.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService implements IVehiculoService{

    @Autowired
    private VehiculoRepository repo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public List<VehiculoDTO> listarVehiculos() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public VehiculoDTO crearVehiculo(Long usuarioId, VehiculoDTO vehiculoDTO) {
        if (repo.existsByPlaca(vehiculoDTO.getPlaca())) {
            throw new DuplicateResourceException("Ya existe un vehículo con placa: " + vehiculoDTO.getPlaca());
        }
        Usuario usuario = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new NotFoundException("Usuario propietario no encontrado con ID: " + usuarioId));

        var vehiculo = Vehiculo.builder()
                .placa(vehiculoDTO.getPlaca().toUpperCase())
                .modelo(vehiculoDTO.getModelo())
                .tipo(vehiculoDTO.getTipo())
                .usuario(usuario)
                .build();
        return Mapper.toDTO(repo.save(vehiculo));
    }

    @Override
    public VehiculoDTO actualizarVehiculo(Long id, VehiculoDTO vehiculoDTO) {
        Vehiculo vehiculo = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Vehículo no encontrado"));
        vehiculo.setModelo(vehiculoDTO.getModelo());
        vehiculo.setPlaca(vehiculoDTO.getPlaca().toUpperCase());
        vehiculo.setTipo(vehiculoDTO.getTipo());
        return Mapper.toDTO(repo.save(vehiculo));
    }

    @Override
    public void eliminarVehiculo(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Vehículo no encontrado");
        }
        repo.deleteById(id);
    }

}
