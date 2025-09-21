package pe.civa.matias_aliaga.interfaces.rest.resources;

import java.util.Date;

/**
 * Resource representation of a bus for REST API responses.
 * Contains comprehensive information about a bus that is exposed to clients.
 *
 * @param id The unique identifier of the bus
 * @param licensePlate The license plate of the bus
 * @param brand The brand name of the bus
 * @param characteristics The characteristics and features of the bus
 * @param busNumber The bus number identifier
 * @param isActive The active status of the bus
 * @param createdAt The timestamp when the bus was created
 */
public record BusResource(
        Long id,
        String licensePlate,
        String brand,
        String characteristics,
        int busNumber,
        boolean isActive,
        Date createdAt
) {
}
