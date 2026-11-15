package ec.edu.espe.sis_estacionamiento.service;

import ec.edu.espe.sis_estacionamiento.dto.UsuarioDTO;
import ec.edu.espe.sis_estacionamiento.exception.DuplicateResourceException;
import ec.edu.espe.sis_estacionamiento.exception.NotFoundException;
import ec.edu.espe.sis_estacionamiento.mapper.Mapper;
import ec.edu.espe.sis_estacionamiento.model.Usuario;
import ec.edu.espe.sis_estacionamiento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements  IUsuarioService{
    @Autowired
    private UsuarioRepository repo;

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDto) {
        if (repo.existsByCedula(usuarioDto.getCedula())) {
            throw new DuplicateResourceException("Ya existe un usuario con la cédula: " + usuarioDto.getCedula());
        }
        var usuario = Usuario.builder()
                .nombre(usuarioDto.getNombre())
                .cedula(usuarioDto.getCedula())
                .telefono(usuarioDto.getTelefono())
                .build();
        return Mapper.toDTO(repo.save(usuario));
    }

    @Override
    public UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuarioDto) {
        Usuario usuario = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setCedula(usuarioDto.getCedula());
        usuario.setTelefono(usuarioDto.getTelefono());
        return Mapper.toDTO(repo.save(usuario));
    }

    @Override
    public void eliminarUsuario(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Usuario no encontrado");
        }
        repo.deleteById(id);
    }

}
