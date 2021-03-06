package com.santander.banco811.service;

import com.santander.banco811.dto.UsuarioRequest;
import com.santander.banco811.dto.UsuarioResponse;
import com.santander.banco811.model.Usuario;
import com.santander.banco811.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> getAll(String nome) {

        if (nome != null) {
            return usuarioRepository.findByNome(nome);
        } else {
            return usuarioRepository.findAll();
        }
    }

    public UsuarioResponse create(UsuarioRequest usuarioRequest) {
        Usuario usuario = new Usuario(usuarioRequest);
        usuarioRepository.save(usuario);

        return new UsuarioResponse(usuario);
    }

    public Usuario getById(Integer id) {
        return usuarioRepository.findById(id).orElseThrow();
    }

    public Usuario update(UsuarioRequest usuarioRequest, Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();

        usuario.setNome(usuarioRequest.getNome());
        usuario.setCpf(usuarioRequest.getCpf());
        usuario.setSenha(usuarioRequest.getSenha());

        return usuarioRepository.save(usuario);
    }

    public void delete(Integer id) {
        var usuario = usuarioRepository.findById(id).orElseThrow();

        usuarioRepository.delete(usuario);
    }
}
