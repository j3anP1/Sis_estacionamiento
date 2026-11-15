package ec.edu.espe.sis_estacionamiento.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//Getter y setter con lombok
@Getter
@Setter
// Constructores con arg y vacío
@AllArgsConstructor
@NoArgsConstructor
@Builder
//
@Entity
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime h_entrada;
    private LocalDateTime  h_salida;
    private BigDecimal valor_pagar;
    //Relacion: Varios registros por un vehículo
    @ManyToOne
    private Vehiculo vehiculo;
    //Relacion: Varios registros por un espacio
    @ManyToOne
    private Espacio espacio;

}
