package ec.edu.espe.sis_estacionamiento.aspect;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAuditAspect {
    private static final Logger log = LoggerFactory.getLogger(ServiceAuditAspect.class);

    // Audita todas las llamadas y tiempos de ejecución dentro del paquete service
    @Around("execution(* ec.edu.espe.sis_estacionamiento.service..*(..))")
    public Object auditarServicios(ProceedingJoinPoint joinPoint) throws Throwable {
        String metodo = joinPoint.getSignature().toShortString();
        String argumentos = Arrays.toString(joinPoint.getArgs());

        log.info("[AOP - AUDITORÍA] Ejecutando servicio: {} | Argumentos: {}", metodo, argumentos);
        long inicio = System.currentTimeMillis();

        Object resultado = joinPoint.proceed();

        long tiempo = System.currentTimeMillis() - inicio;
        log.info("[AOP - RENDIMIENTO] Servicio {} finalizado en {} ms", metodo, tiempo);

        return resultado;
    }

    // Registra cualquier excepción lanzada desde los servicios
    @AfterThrowing(pointcut = "execution(* ec.edu.espe.sis_estacionamiento.service..*(..))", throwing = "ex")
    public void registrarErrorServicio(JoinPoint joinPoint, Throwable ex) {
        String metodo = joinPoint.getSignature().toShortString();
        log.error("[AOP - ERROR] Excepción en {}: {} -> {}",
                metodo, ex.getClass().getSimpleName(), ex.getMessage());
    }
}
