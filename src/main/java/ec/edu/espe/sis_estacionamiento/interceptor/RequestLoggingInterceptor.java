package ec.edu.espe.sis_estacionamiento.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(RequestLoggingInterceptor.class);
    private static final String START_TIME_ATTR = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        long startTime = System.currentTimeMillis();
        request.setAttribute(START_TIME_ATTR, startTime);

        String clientIp = request.getRemoteAddr();
        log.info("[INTERCEPTOR - INICIO] Método: {} | URI: {} | IP Cliente: {}",
                request.getMethod(), request.getRequestURI(), clientIp);
        return true; // Permite continuar hacia el Controller
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        long startTime = (Long) request.getAttribute(START_TIME_ATTR);
        long duration = System.currentTimeMillis() - startTime;

        log.info("[INTERCEPTOR - FIN] URI: {} | Status HTTP: {} | Tiempo de ejecución: {} ms",
                request.getRequestURI(), response.getStatus(), duration);
    }
}
