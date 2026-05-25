package com.duoc.learningplatform.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    // Aplica a todos los metodos de todos los services
    @Pointcut("execution(* com.duoc.learningplatform.service.*.*(..))")
    public void serviceLayer() {}

    // Aplica a todos los metodos de todos los controllers
    @Pointcut("execution(* com.duoc.learningplatform.controller.*.*(..))")
    public void controllerLayer() {}

    // Log ANTES de ejecutar cualquier metodo del service
    @Before("serviceLayer()")
    public void logBeforeService(JoinPoint joinPoint) {
        logger.info("[SERVICE] Llamando: " + joinPoint.getSignature().getName()
                + " | Args: " + Arrays.toString(joinPoint.getArgs()));
    }

    // Log DESPUES de ejecutar con exito
    @AfterReturning(pointcut = "serviceLayer()", returning = "resultado")
    public void logAfterService(JoinPoint joinPoint, Object resultado) {
        logger.info("[SERVICE] Completado: " + joinPoint.getSignature().getName()
                + " | Resultado: " + resultado);
    }

    // Log de tiempo de ejecucion en controllers (Around)
    @Around("controllerLayer()")
    public Object logTiempoEjecucion(ProceedingJoinPoint joinPoint) throws Throwable {
        long inicio = System.currentTimeMillis();
        Object resultado = joinPoint.proceed();
        long duracion = System.currentTimeMillis() - inicio;
        logger.info("[CONTROLLER] " + joinPoint.getSignature().getName()
                + " ejecutado en " + duracion + "ms");
        return resultado;
    }

    // Log de excepciones en cualquier capa
    @AfterThrowing(pointcut = "serviceLayer() || controllerLayer()", throwing = "ex")
    public void logExcepcion(JoinPoint joinPoint, Exception ex) {
        logger.warning("[ERROR] En: " + joinPoint.getSignature().getName()
                + " | Excepcion: " + ex.getMessage());
    }
}
