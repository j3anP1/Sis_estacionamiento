package ec.edu.espe.sis_estacionamiento.repository;

import ec.edu.espe.sis_estacionamiento.model.Espacio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EspacioRepository extends JpaRepository<Espacio, Long> {
    // Consulta personalizada: Determinar disponibilidad de espacios (estado = true)
    List<Espacio> findByEstadoTrue();
    List<Espacio> findByEstadoTrueAndTipoIgnoreCase(String tipo);
}
