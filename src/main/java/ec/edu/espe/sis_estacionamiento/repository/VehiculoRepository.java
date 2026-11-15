package ec.edu.espe.sis_estacionamiento.repository;

import ec.edu.espe.sis_estacionamiento.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    boolean existsByPlaca(String placa);
}
