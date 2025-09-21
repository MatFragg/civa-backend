package pe.civa.matias_aliaga.interfaces.rest.transform;

import pe.civa.matias_aliaga.domain.model.commands.CreateBusCommand;
import pe.civa.matias_aliaga.interfaces.rest.resources.CreateBusResource;

/**
 * Assembler class for converting CreateBusResource DTOs to CreateBusCommand objects.
 * Provides static methods to transform REST API resources to domain commands.
 */
public class CreateBusCommandFromResourceAssembler {

    /**
     * Converts a CreateBusResource DTO to a CreateBusCommand domain object.
     * Transforms the REST API request data into a format suitable for domain operations.
     *
     * @param resource The CreateBusResource DTO to convert
     * @return A CreateBusCommand containing the resource data formatted for domain processing
     */
    public static CreateBusCommand toCommand(CreateBusResource resource) {
      return new CreateBusCommand(
              resource.busNumber(),
              resource.licensePlate(),
              resource.characteristics(),
              resource.brand(),
              resource.isActive()
      );
    }
}
