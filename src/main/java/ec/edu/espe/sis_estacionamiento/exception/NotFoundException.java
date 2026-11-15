package ec.edu.espe.sis_estacionamiento.exception;

// 1. NotFoundException (404 Not Found)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) { super(message); }
}
