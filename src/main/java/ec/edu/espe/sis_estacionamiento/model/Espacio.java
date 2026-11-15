package ec.edu.espe.sis_estacionamiento.model;

import jakarta.persistence.*;
import lombok.*;

//Getter y setter con lombok
@Getter
@Setter
// Constructores con arg y vacío
@AllArgsConstructor
@NoArgsConstructor
//
@Entity
@Builder
public class Espacio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private boolean estado;
    @Column(nullable = false, length = 30)
    private String tipo;
    @Column(nullable = false)
    private Double tarifa_hora;
}
