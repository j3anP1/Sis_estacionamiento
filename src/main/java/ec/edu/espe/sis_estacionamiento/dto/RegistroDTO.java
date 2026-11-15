package ec.edu.espe.sis_estacionamiento.dto;

import ec.edu.espe.sis_estacionamiento.model.Espacio;
import ec.edu.espe.sis_estacionamiento.model.Vehiculo;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//Mappear DTO
@Builder
public class RegistroDTO {
    private Long id;
    private LocalDateTime h_entrada;
    private LocalDateTime  h_salida;
    private BigDecimal valor_pagar;
    //h_salida - hora_entrada
    private Long tiempo_total;

    private Vehiculo vehiculo;
    private Espacio espacio;
}
