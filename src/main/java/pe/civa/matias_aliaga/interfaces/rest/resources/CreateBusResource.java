package pe.civa.matias_aliaga.interfaces.rest.resources;

import jakarta.validation.constraints.*;

/**
 * Resource for creating a new bus through REST API requests.
 * Contains validation constraints to ensure data integrity when creating buses.
 *
 * @param busNumber The bus number (must be greater than 0)
 * @param licensePlate The license plate in format AA1-234 (required, not blank)
 * @param characteristics The bus characteristics and features (required, not blank)
 * @param brand The bus brand name (required, not blank)
 * @param isActive The initial active status of the bus
 */
public record CreateBusResource(

        @Min(value = 1, message = "Bus number must be greater than 0")
        int busNumber,

        @NotBlank(message = "License plate cannot be blank")
        @Pattern(regexp = "^[A-Z]\\d[A-Z]-\\d{3}$",
                message = "Invalid license plate format. Expected format: AA1-234")
        String licensePlate,

        @NotBlank(message = "Characteristics cannot be blank")
        String characteristics,

        @NotBlank(message = "Brand cannot be blank")
        String brand,
        boolean isActive
) {
}
