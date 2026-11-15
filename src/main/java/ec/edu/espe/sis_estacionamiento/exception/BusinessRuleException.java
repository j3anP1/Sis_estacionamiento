package ec.edu.espe.sis_estacionamiento.exception;

public class BusinessRuleException extends RuntimeException {
    // BusinessRuleException (400 Bad Request / 409 Conflict - Reglas de negocio)
    public BusinessRuleException(String message) {
        super(message);
    }
}
