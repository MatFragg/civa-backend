package pe.civa.matias_aliaga.shared.interfaces.rest.responses;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Record representing an error response for REST API operations.
 * Used to standardize error information returned to clients when operations fail.
 *
 * @param status The HTTP status code of the error response
 * @param errors List of error messages describing what went wrong
 * @param timestamp The exact time when the error occurred
 */
public record ErrorResponse(
        int status,
        List<String> errors,
        LocalDateTime timestamp
) {}