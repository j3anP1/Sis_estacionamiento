package ec.edu.espe.sis_estacionamiento.service;

import ec.edu.espe.sis_estacionamiento.dto.UsuarioDTO;

import java.util.List;

public interface IUsuarioService {
    List<UsuarioDTO> listarUsuarios();
    UsuarioDTO crearUsuario (UsuarioDTO usuarioDto);
    UsuarioDTO actualizarUsuario (Long id, UsuarioDTO usuarioDto);
    void eliminarUsuario (Long id);

}
