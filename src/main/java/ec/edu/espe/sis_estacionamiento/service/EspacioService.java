package ec.edu.espe.sis_estacionamiento.service;

import ec.edu.espe.sis_estacionamiento.dto.EspacioDTO;
import ec.edu.espe.sis_estacionamiento.exception.BusinessRuleException;
import ec.edu.espe.sis_estacionamiento.exception.NotFoundException;
import ec.edu.espe.sis_estacionamiento.mapper.Mapper;
import ec.edu.espe.sis_estacionamiento.model.Espacio;
import ec.edu.espe.sis_estacionamiento.repository.EspacioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspacioService implements IEspacioService{
    @Autowired
    private EspacioRepository repo;

    @Override
    public List<EspacioDTO> listarEspacios() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public List<EspacioDTO> listarDisponibles() {
        return repo.findByEstadoTrue().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public EspacioDTO crearEspacio(EspacioDTO espacioDTO) {
        if (espacioDTO.getTarifa_hora() == null || espacioDTO.getTarifa_hora() <= 0) {
            throw new BusinessRuleException("La tarifa por hora debe ser mayor a 0");
        }
        var espacio = Espacio.builder()
                .estado(true) //Todo espacio nuevo inicia disponible
                .tipo(espacioDTO.getTipo())
                .tarifa_hora(espacioDTO.getTarifa_hora())
                .build();
        return Mapper.toDTO(repo.save(espacio));
    }

    @Override
    public EspacioDTO actualizarEspacio(Long id, EspacioDTO espacioDTO) {
        Espacio espacio = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Espacio no encontrado"));
        espacio.setEstado(espacioDTO.isEstado());
        espacio.setTarifa_hora(espacioDTO.getTarifa_hora());
        espacio.setTipo(espacioDTO.getTipo());
        return Mapper.toDTO(repo.save(espacio));
    }

    @Override
    public void eliminarEspacio(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Espacio no encontrado");
        }
        repo.deleteById(id);
    }
}
