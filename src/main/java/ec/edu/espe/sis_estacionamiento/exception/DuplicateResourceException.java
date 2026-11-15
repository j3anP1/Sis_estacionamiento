package ec.edu.espe.sis_estacionamiento.exception;

public class DuplicateResourceException extends RuntimeException {
    //DuplicateResourceException (409 Conflict - Placas o cédulas duplicadas)
    public DuplicateResourceException(String message) {
        super(message);
    }
}
