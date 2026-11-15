package ec.edu.espe.sis_estacionamiento.dto;

import ec.edu.espe.sis_estacionamiento.model.Usuario;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//Mappear DTO
@Builder
public class VehiculoDTO {
    private Long id;
    private String placa;
    private String modelo;
    private String tipo;

    private Usuario usuario;
}
