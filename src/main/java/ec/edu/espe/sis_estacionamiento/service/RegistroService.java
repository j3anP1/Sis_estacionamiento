package ec.edu.espe.sis_estacionamiento.service;

import ec.edu.espe.sis_estacionamiento.dto.RegistroDTO;
import ec.edu.espe.sis_estacionamiento.exception.BusinessRuleException;
import ec.edu.espe.sis_estacionamiento.exception.NotFoundException;
import ec.edu.espe.sis_estacionamiento.mapper.Mapper;
import ec.edu.espe.sis_estacionamiento.model.Espacio;
import ec.edu.espe.sis_estacionamiento.model.Registro;
import ec.edu.espe.sis_estacionamiento.model.Vehiculo;
import ec.edu.espe.sis_estacionamiento.repository.EspacioRepository;
import ec.edu.espe.sis_estacionamiento.repository.RegistroRepository;
import ec.edu.espe.sis_estacionamiento.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistroService implements IRegistroService{
    @Autowired
    private RegistroRepository repo;

    @Autowired
    private VehiculoRepository vehiculoRepo;

    @Autowired
    private EspacioRepository espacioRepo;

    @Override
    public List<RegistroDTO> traerRegistros() {
        return repo.findAll().stream().map(this::mapearConTiempo).toList();
    }

    // PROCESO: CONTROLAR INGRESO
    @Override
    @Transactional
    public RegistroDTO crearRegistro(RegistroDTO registroDTO) {
        Long vehiculoId = registroDTO.getVehiculo().getId();
        Long espacioId = registroDTO.getEspacio().getId();

        Vehiculo vehiculo = vehiculoRepo.findById(vehiculoId)
                .orElseThrow(() -> new NotFoundException("Vehículo no encontrado"));
        Espacio espacio = espacioRepo.findById(espacioId)
                .orElseThrow(() -> new NotFoundException("Espacio no encontrado"));

        // Regla: Vehículo no puede ingresar dos veces sin haber salido
        if (repo.findRegistroActivoPorVehiculo(vehiculoId).isPresent()) {
            throw new BusinessRuleException("El vehículo con placa " + vehiculo.getPlaca() + " ya se encuentra estacionado");
        }

        // Regla: Espacio debe estar disponible (true)
        if (!espacio.isEstado()) {
            throw new BusinessRuleException("El espacio #" + espacio.getId() + " está ocupado");
        }

        // Regla: Compatibilidad de tipo (ej. Auto en espacio de Auto)
        if (!espacio.getTipo().equalsIgnoreCase(vehiculo.getTipo())) {
            throw new BusinessRuleException("El espacio es para " + espacio.getTipo() + " y el vehículo es " + vehiculo.getTipo());
        }

        // Ocupar espacio
        espacio.setEstado(false);
        espacioRepo.save(espacio);

        var registro = Registro.builder()
                .h_entrada(LocalDateTime.now())
                .vehiculo(vehiculo)
                .espacio(espacio)
                .build();

        return Mapper.toDTO(repo.save(registro));
    }

    // PROCESO: CONTROLAR SALIDA Y CALCULAR PERMANENCIA
    @Override
    @Transactional
    public RegistroDTO registrarSalida(Long registroId) {
        Registro registro = repo.findById(registroId)
                .orElseThrow(() -> new NotFoundException("Registro no encontrado"));

        if (registro.getH_salida() != null) {
            throw new BusinessRuleException("Este registro ya tiene una salida procesada");
        }

        LocalDateTime salida = LocalDateTime.now();
        registro.setH_salida(salida);

        // Calcular tiempo de permanencia en minutos y horas (mínimo 1 hora)
        long minutosTotales = Duration.between(registro.getH_entrada(), salida).toMinutes();
        long horasACobrar = Math.max(1, (long) Math.ceil(minutosTotales / 60.0));

        BigDecimal tarifa = BigDecimal.valueOf(registro.getEspacio().getTarifa_hora());
        registro.setValor_pagar(tarifa.multiply(BigDecimal.valueOf(horasACobrar)));

        // Liberar espacio (true = disponible)
        Espacio espacio = registro.getEspacio();
        espacio.setEstado(true);
        espacioRepo.save(espacio);

        Registro guardado = repo.save(registro);
        RegistroDTO dto = Mapper.toDTO(guardado);
        dto.setTiempo_total(minutosTotales); // Devolvemos tiempo de permanencia en minutos
        return dto;
    }

    @Override
    public RegistroDTO actualizarRegistro(Long id, RegistroDTO registroDTO) {
        Registro registro = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Registro no encontrado"));
        registro.setH_entrada(registroDTO.getH_entrada());
        registro.setH_salida(registroDTO.getH_salida());
        registro.setValor_pagar(registroDTO.getValor_pagar());
        return mapearConTiempo(repo.save(registro));
    }

    @Override
    public void eliminarRegistro(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Registro no encontrado");
        }
        repo.deleteById(id);
    }

    private RegistroDTO mapearConTiempo(Registro r) {
        RegistroDTO dto = Mapper.toDTO(r);
        if (r.getH_entrada() != null && r.getH_salida() != null) {
            dto.setTiempo_total(Duration.between(r.getH_entrada(), r.getH_salida()).toMinutes());
        }
        return dto;
    }
}
