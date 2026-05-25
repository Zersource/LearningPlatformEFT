package com.duoc.learningplatform.service;

import com.duoc.learningplatform.exception.ResourceNotFoundException;
import com.duoc.learningplatform.model.Tarea;
import com.duoc.learningplatform.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public List<Tarea> obtenerTodas() {
        return tareaRepository.findAll();
    }

    public Tarea obtenerPorId(Long id) {
        return tareaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea", id));
    }

    public List<Tarea> obtenerPorCurso(Long cursoId) {
        return tareaRepository.findByCursoId(cursoId);
    }

    public List<Tarea> obtenerPorEstado(String estado) {
        return tareaRepository.findByEstado(estado.toUpperCase());
    }

    public Tarea crear(Tarea tarea) {
        if (tarea.getEstado() == null) {
            tarea.setEstado("PENDIENTE");
        }
        return tareaRepository.save(tarea);
    }

    public Tarea actualizar(Long id, Tarea tareaActualizada) {
        Tarea existente = obtenerPorId(id);
        existente.setTitulo(tareaActualizada.getTitulo());
        existente.setDescripcion(tareaActualizada.getDescripcion());
        existente.setCursoId(tareaActualizada.getCursoId());
        existente.setFechaEntrega(tareaActualizada.getFechaEntrega());
        existente.setEstado(tareaActualizada.getEstado().toUpperCase());
        return tareaRepository.save(existente);
    }

    public void eliminar(Long id) {
        Tarea tarea = obtenerPorId(id);
        tareaRepository.delete(tarea);
    }
}
