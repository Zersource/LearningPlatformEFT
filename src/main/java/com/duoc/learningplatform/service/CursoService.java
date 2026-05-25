package com.duoc.learningplatform.service;

import com.duoc.learningplatform.exception.ResourceNotFoundException;
import com.duoc.learningplatform.model.Curso;
import com.duoc.learningplatform.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    // Obtener todos los cursos
    public List<Curso> obtenerTodos() {
        return cursoRepository.findAll();
    }

    // Obtener solo cursos activos
    public List<Curso> obtenerActivos() {
        return cursoRepository.findByActivoTrue();
    }

    // Obtener un curso por ID
    public Curso obtenerPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso", id));
    }

    // Crear un nuevo curso
    public Curso crear(Curso curso) {
        return cursoRepository.save(curso);
    }

    // Actualizar un curso existente
    public Curso actualizar(Long id, Curso cursoActualizado) {
        Curso cursoExistente = obtenerPorId(id);
        cursoExistente.setNombre(cursoActualizado.getNombre());
        cursoExistente.setDescripcion(cursoActualizado.getDescripcion());
        cursoExistente.setDuracion(cursoActualizado.getDuracion());
        cursoExistente.setPrecio(cursoActualizado.getPrecio());
        cursoExistente.setActivo(cursoActualizado.getActivo());
        return cursoRepository.save(cursoExistente);
    }

    // Eliminar un curso
    public void eliminar(Long id) {
        Curso curso = obtenerPorId(id);
        cursoRepository.delete(curso);
    }

    // Buscar cursos por nombre
    public List<Curso> buscarPorNombre(String nombre) {
        return cursoRepository.findByNombreContainingIgnoreCase(nombre);
    }
}
