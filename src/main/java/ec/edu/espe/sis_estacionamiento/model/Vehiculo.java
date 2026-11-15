package ec.edu.espe.sis_estacionamiento.model;

import jakarta.persistence.*;
import lombok.*;

//Getter y setter con lombok
@Getter
@Setter
// Constructores con arg y vacío
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 15)
    private String placa;

    @Column(nullable = false, length = 50)
    private String modelo;

    @Column(nullable = false, length = 30)
    private String tipo;

    //Relación: Muchos vehiculos a Un usuario
    @ManyToOne
    private Usuario usuario;


}
