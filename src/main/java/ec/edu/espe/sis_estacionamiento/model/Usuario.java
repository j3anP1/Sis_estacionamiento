package ec.edu.espe.sis_estacionamiento.model;


import jakarta.persistence.*;
import lombok.*;

//Getter y setter con lombok
@Getter @Setter
// Constructores con arg y vacío
@AllArgsConstructor @NoArgsConstructor
//
@Entity
@Builder
public class Usuario {
    //Mapeo con JPA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 20)
    private String cedula;

    @Column(nullable = false, length = 20)
    private String telefono;
}