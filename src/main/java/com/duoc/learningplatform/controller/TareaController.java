package com.duoc.learningplatform.controller;

import com.duoc.learningplatform.model.Tarea;
import com.duoc.learningplatform.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping
    public ResponseEntity<List<Tarea>> obtenerTodas() {
        return ResponseEntity.ok(tareaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tareaService.obtenerPorId(id));
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Tarea>> obtenerPorCurso(@PathVariable Long cursoId) {
        return ResponseEntity.ok(tareaService.obtenerPorCurso(cursoId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Tarea>> obtenerPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(tareaService.obtenerPorEstado(estado));
    }

    @PostMapping
    public ResponseEntity<Tarea> crear(@RequestBody Tarea tarea) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tareaService.crear(tarea));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizar(@PathVariable Long id, @RequestBody Tarea tarea) {
        return ResponseEntity.ok(tareaService.actualizar(id, tarea));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tareaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
