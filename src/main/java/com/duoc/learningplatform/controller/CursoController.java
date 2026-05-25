package com.duoc.learningplatform.controller;

import com.duoc.learningplatform.model.Curso;
import com.duoc.learningplatform.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    // GET /api/cursos → todos los cursos
    @GetMapping
    public ResponseEntity<List<Curso>> obtenerTodos() {
        return ResponseEntity.ok(cursoService.obtenerTodos());
    }

    // GET /api/cursos/activos → solo cursos activos
    @GetMapping("/activos")
    public ResponseEntity<List<Curso>> obtenerActivos() {
        return ResponseEntity.ok(cursoService.obtenerActivos());
    }

    // GET /api/cursos/{id} → un curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.obtenerPorId(id));
    }

    // GET /api/cursos/buscar?nombre=xxx → buscar por nombre
    @GetMapping("/buscar")
    public ResponseEntity<List<Curso>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(cursoService.buscarPorNombre(nombre));
    }

    // POST /api/cursos → crear nuevo curso
    @PostMapping
    public ResponseEntity<Curso> crear(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoService.crear(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCurso);
    }

    // PUT /api/cursos/{id} → actualizar curso completo
    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizar(@PathVariable Long id, @RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.actualizar(id, curso));
    }

    // DELETE /api/cursos/{id} → eliminar curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cursoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
