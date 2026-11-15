package ec.edu.espe.sis_estacionamiento.service;

import ec.edu.espe.sis_estacionamiento.dto.EspacioDTO;

import java.util.List;

public interface IEspacioService {
    List<EspacioDTO> listarEspacios();
    List<EspacioDTO> listarDisponibles();
    EspacioDTO crearEspacio(EspacioDTO espacioDTO);
    EspacioDTO actualizarEspacio(Long id, EspacioDTO espacioDTO);
    void eliminarEspacio(Long id);
}
