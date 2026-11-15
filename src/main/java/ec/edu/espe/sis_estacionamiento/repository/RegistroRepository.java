package ec.edu.espe.sis_estacionamiento.repository;

import ec.edu.espe.sis_estacionamiento.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RegistroRepository extends JpaRepository<Registro,Long> {
    // Consulta personalizada: Verificar si un vehículo ya está adentro (sin hora de salida)
    @Query("SELECT r FROM Registro r WHERE r.vehiculo.id = :vehiculoId AND r.h_salida IS NULL")
    Optional<Registro> findRegistroActivoPorVehiculo(@Param("vehiculoId") Long vehiculoId);
}
