package pe.civa.matias_aliaga.interfaces.rest.transform;

import pe.civa.matias_aliaga.domain.model.entities.BusBrand;
import pe.civa.matias_aliaga.interfaces.rest.resources.BusBrandResource;

/**
 * Assembler class for converting BusBrand entities to BusBrandResource DTOs.
 * Provides static methods to transform domain entities to REST API resource representations.
 */
public class BusBrandResourceFromEntityAssembler {

    /**
     * Converts a BusBrand entity to a BusBrandResource DTO.
     * Transforms the domain model into a format suitable for REST API responses.
     *
     * @param entity The BusBrand entity to convert
     * @return A BusBrandResource containing the entity data formatted for API responses
     */
    public static BusBrandResource toResource(BusBrand entity) {
        return new BusBrandResource(
                entity.getId(),
                entity.getStringName()
        );
    }
}
