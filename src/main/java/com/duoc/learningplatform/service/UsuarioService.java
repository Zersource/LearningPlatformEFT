package com.duoc.learningplatform.service;

import com.duoc.learningplatform.exception.ResourceNotFoundException;
import com.duoc.learningplatform.model.Usuario;
import com.duoc.learningplatform.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));
    }

    public Usuario obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con email " + email + " no encontrado"));
    }

    public List<Usuario> obtenerPorRol(String rol) {
        return usuarioRepository.findByRol(rol.toUpperCase());
    }

    public Usuario crear(Usuario usuario) {
        usuario.setRol(usuario.getRol().toUpperCase());
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizar(Long id, Usuario usuarioActualizado) {
        Usuario existente = obtenerPorId(id);
        existente.setNombre(usuarioActualizado.getNombre());
        existente.setEmail(usuarioActualizado.getEmail());
        existente.setRol(usuarioActualizado.getRol().toUpperCase());
        existente.setActivo(usuarioActualizado.getActivo());
        return usuarioRepository.save(existente);
    }

    public void eliminar(Long id) {
        Usuario usuario = obtenerPorId(id);
        usuarioRepository.delete(usuario);
    }
}
