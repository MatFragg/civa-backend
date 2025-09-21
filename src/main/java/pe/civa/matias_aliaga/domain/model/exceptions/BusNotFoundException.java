package pe.civa.matias_aliaga.domain.model.exceptions;

/**
 * Exception thrown when a bus is not found in the system.
 * Used to indicate that a requested bus does not exist.
 */
public class BusNotFoundException extends RuntimeException {
    /**
     * Constructs a new BusNotFoundException with the specified detail message.
     * @param message The detail message explaining why the bus was not found
     */
    public BusNotFoundException(String message) {
        super(message);
    }
}