package com.javanauta.cadastro_usuario.business;

import com.javanauta.cadastro_usuario.infrastructure.entitys.Usuario;
import com.javanauta.cadastro_usuario.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService (UsuarioRepository usuarioRepository) {

        this.usuarioRepository = usuarioRepository;

    }

    public void salvarUsuario(Usuario usuario) {
        usuarioRepository.saveAndFlush(usuario);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("email não encontrado")
        );
    }

    public void deletarUsuario(String email) {
        usuarioRepository.deleteByEmail(email);
    }

    public void atualizarUsuarioPorId(Integer id, Usuario usuario) {
        Usuario usuarioEntity = usuarioRepository.findById(id).orElseThrow(() -> new  RuntimeException("usuário não encontrado"));
        Usuario usuarioAtualizado = Usuario.builder()
                .email(usuario.getEmail() != null ? usuario.getEmail() :
                        usuarioEntity.getEmail())
                .nome(usuario.getNome() != null ? usuario.getNome():
                        usuarioEntity.getNome())
                .id(usuarioEntity.getId())
                .build();
        usuarioRepository.saveAndFlush(usuarioAtualizado);
    }


}
