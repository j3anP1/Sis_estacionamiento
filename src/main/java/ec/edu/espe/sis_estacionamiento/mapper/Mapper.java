package ec.edu.espe.sis_estacionamiento.mapper;

import ec.edu.espe.sis_estacionamiento.dto.EspacioDTO;
import ec.edu.espe.sis_estacionamiento.dto.RegistroDTO;
import ec.edu.espe.sis_estacionamiento.dto.UsuarioDTO;
import ec.edu.espe.sis_estacionamiento.dto.VehiculoDTO;
import ec.edu.espe.sis_estacionamiento.model.Espacio;
import ec.edu.espe.sis_estacionamiento.model.Registro;
import ec.edu.espe.sis_estacionamiento.model.Usuario;
import ec.edu.espe.sis_estacionamiento.model.Vehiculo;

import java.time.Duration;

public class Mapper {

    //Mapeo de Usuario a UsuarioDTO
    public static UsuarioDTO toDTO(Usuario u) {
        if (u == null) return null;

        return UsuarioDTO.builder()
                .id(u.getId())
                .nombre(u.getNombre())
                .cedula(u.getCedula())
                .telefono(u.getTelefono())
                .build();
    }

    //Mapeo de Vehiculo a VehiculoDTO
    public static VehiculoDTO toDTO(Vehiculo v) {
        if (v == null) return null;

        return VehiculoDTO.builder()
                .id(v.getId())
                .placa(v.getPlaca())
                .modelo(v.getModelo())
                .tipo(v.getTipo())
                .usuario(v.getUsuario())
                .build();
    }
    //Mapeo de Espacio a EspacioDTO
    public static EspacioDTO toDTO(Espacio e) {
        if (e == null) return null;

        return EspacioDTO.builder()
                .id(e.getId())
                .estado(e.isEstado())
                .tipo(e.getTipo())
                .tarifa_hora(e.getTarifa_hora())
                .build();
    }

    //Mapeo de Registro a RegistroDTO
    public static RegistroDTO toDTO(Registro r) {
        if (r == null) return null;

        Long tiempoTotal = null;

        if (r.getH_entrada() != null && r.getH_salida() != null) {
            tiempoTotal = Duration.between(
                    r.getH_entrada(),
                    r.getH_salida()
            ).toMinutes();
        }

        return RegistroDTO.builder()
                .id(r.getId())
                .h_entrada(r.getH_entrada())
                .h_salida(r.getH_salida())
                .valor_pagar(r.getValor_pagar())
                .vehiculo(r.getVehiculo())
                .espacio(r.getEspacio())
                .tiempo_total(tiempoTotal)
                .build();
    }



}
