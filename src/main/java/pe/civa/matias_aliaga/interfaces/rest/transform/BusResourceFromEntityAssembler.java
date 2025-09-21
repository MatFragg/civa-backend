package pe.civa.matias_aliaga.interfaces.rest.transform;

import pe.civa.matias_aliaga.domain.model.entities.Bus;
import pe.civa.matias_aliaga.interfaces.rest.resources.BusResource;

/**
 * Assembler class for converting Bus entities to BusResource DTOs.
 * Provides static methods to transform domain entities to REST API resource representations.
 */
public class BusResourceFromEntityAssembler {

    /**
     * Converts a Bus entity to a BusResource DTO.
     * Transforms the domain model into a format suitable for REST API responses.
     * Extracts all necessary fields including license plate value and brand name.
     *
     * @param entity The Bus entity to convert
     * @return A BusResource containing the entity data formatted for API responses
     */
    public static BusResource toResource(Bus entity) {
        return new BusResource(
                entity.getId(),
                entity.getLicensePlate().getValue(),
                entity.getBrand().getStringName(),
                entity.getCharacteristics(),
                entity.getBusNumber(),
                entity.isActive(),
                entity.getCreatedAt()
        );
    }
}
