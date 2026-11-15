package ec.edu.espe.sis_estacionamiento.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//Mappear DTO
@Builder
public class EspacioDTO {
    private Long id;
    private boolean estado;
    private String tipo;
    private Double tarifa_hora;
}
