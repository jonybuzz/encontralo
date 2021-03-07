package com.jonybuzz.encontralo.config.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Esta clase tiene como objetivo loggear los eventos ocurridos en la capa de
 * presentacion o control para auditoria o depuracion. Los advice creados son
 * una base y deben ser personalizados de acuerdo al comportamiento de la
 * aplicacion.
 *
 * <p>
 * Por ejemplo, si se incluye un mecanismo de seguridad como Spring Security es
 * recomendable incluir el username en el log:
 * </p>
 * <pre class="code">
 * SecurityContextHolder.getContext().getAuthentication().getName()
 * </pre>
 *
 * @see <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop">Spring
 * AOP</a>
 */
@Aspect
@Configuration
@Slf4j
public class ControllerLoggingAspect {

    @Before("within(com.jonybuzz.*.controller..*)")
    public void loggearInputDeController(JoinPoint joinPoint) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (log.isInfoEnabled()) {
            if (auth == null) {
                log.info("[Ejecucion] Anonimo => {}. Parametros: {}",
                        joinPoint.getSignature().toShortString(),
                        joinPoint.getArgs());
            } else {
                log.info("[Ejecucion] {}/{} => {}. Parametros: {}",
                        SecurityContextHolder.getContext().getAuthentication().getName(),
                        SecurityContextHolder.getContext().getAuthentication().getAuthorities(),
                        joinPoint.getSignature().toShortString(),
                        joinPoint.getArgs());
            }
        }
    }

    @AfterReturning(pointcut = "within(com.jonybuzz.*.controller.rest..*)", returning = "resultado")
    public void loggearEjecucionDeController(JoinPoint joinPoint, Object resultado) {
        if (log.isInfoEnabled()) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null) {
                log.info("[Respuesta] Anonimo => {}. Parametros: {}",
                        joinPoint.getSignature().toShortString(),
                        resultado);
            } else {
                log.info("[Respuesta] {} => {}. Parametros: {}",
                        SecurityContextHolder.getContext().getAuthentication().getName(),
                        joinPoint.getSignature().toShortString(),
                        resultado);
            }
        }
    }

    @AfterThrowing(pointcut = "within(com.jonybuzz.*.service..*)", throwing = "exception")
    public void loggearExcepcionesDeService(Exception exception) {
        log.debug("[Service Error]", exception);
    }
}
