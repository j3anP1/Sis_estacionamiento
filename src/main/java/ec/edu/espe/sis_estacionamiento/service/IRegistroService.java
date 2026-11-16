package ec.edu.espe.sis_estacionamiento.service;

import ec.edu.espe.sis_estacionamiento.dto.RegistroDTO;
import java.util.List;

public interface IRegistroService {
    List<RegistroDTO> traerRegistros();
    RegistroDTO registrarSalida(Long registroId);
    RegistroDTO crearRegistro(RegistroDTO registroDTO );
    RegistroDTO actualizarRegistro(Long id, RegistroDTO registroDTO);
    void eliminarRegistro (Long id);

}
