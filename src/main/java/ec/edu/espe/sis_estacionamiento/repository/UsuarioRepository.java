package ec.edu.espe.sis_estacionamiento.repository;

import ec.edu.espe.sis_estacionamiento.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByCedula(String cedula);
}
