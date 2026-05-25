-- ==========================================
-- Datos iniciales para LearningPlatformEFT
-- Actualizado: Spring Security + BCrypt
-- ==========================================

-- Usuarios (password hasheado con BCrypt)
-- ana.garcia@duoc.cl     → estudiante123
-- carlos.lopez@duoc.cl   → profesor123
-- maria.torres@duoc.cl   → estudiante123
-- admin@duoc.cl          → admin123

INSERT INTO usuarios (nombre, email, password, rol, activo) VALUES
    ('Ana García', 'ana.garcia@duoc.cl', '$2b$10$.vlpLnb7Lky2Ur.DDQ9ykeai.gdDkfwtWHdCC.o8fvLtwWoEEWW0i', 'ESTUDIANTE', true);
INSERT INTO usuarios (nombre, email, password, rol, activo) VALUES
    ('Carlos López', 'carlos.lopez@duoc.cl', '$2b$10$v3HMkIDIhhb/hrorVw/Fhui9SDKFjAte4EQ1CCO5WmbKCh4o83paK', 'PROFESOR', true);
INSERT INTO usuarios (nombre, email, password, rol, activo) VALUES
    ('María Torres', 'maria.torres@duoc.cl', '$2b$10$ePVhSEvosaJ67NlIDsFEMeygWczTn/KEhicl7qIVOWO/f8zN3k6Aq', 'ESTUDIANTE', true);
INSERT INTO usuarios (nombre, email, password, rol, activo) VALUES
    ('Administrador', 'admin@duoc.cl', '$2b$10$kYZYWxgRnL355wT34mS5M.7BLjtUqq0.1cUIN02pwa20PB72YldJW', 'ADMIN', true);

-- Cursos (sin cambios)
INSERT INTO cursos (nombre, descripcion, duracion, precio, activo) VALUES
    ('Desarrollo Backend I', 'Fundamentos de microservicios con Spring Boot', 40, 59990.0, true);
INSERT INTO cursos (nombre, descripcion, duracion, precio, activo) VALUES
    ('Java Avanzado', 'Patrones de diseño y buenas practicas en Java', 60, 79990.0, true);
INSERT INTO cursos (nombre, descripcion, duracion, precio, activo) VALUES
    ('Spring Boot desde cero', 'Curso introductorio a Spring Boot y REST APIs', 30, 49990.0, true);

-- Tareas (sin cambios)
INSERT INTO tareas (titulo, descripcion, curso_id, fecha_entrega, estado) VALUES
    ('Tarea 1 - Microservicios', 'Diseniar arquitectura de microservicios', 1, '2025-05-20', 'PENDIENTE');
INSERT INTO tareas (titulo, descripcion, curso_id, fecha_entrega, estado) VALUES
    ('Tarea 2 - Spring Boot', 'Implementar CRUD con Spring Boot', 1, '2025-05-27', 'PENDIENTE');
INSERT INTO tareas (titulo, descripcion, curso_id, fecha_entrega, estado) VALUES
    ('Patrones GoF', 'Implementar patron Strategy y Observer', 2, '2025-05-25', 'ENTREGADA');