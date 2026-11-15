package ec.edu.espe.sis_estacionamiento.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//Mappear DTO
@Builder
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String cedula;
    private String telefono;
}
