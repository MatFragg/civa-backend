package pe.civa.matias_aliaga.interfaces.rest.resources;

/**
 * Resource representation of a bus brand for REST API responses.
 * Contains the essential information about a bus brand that is exposed to clients.
 *
 * @param id The unique identifier of the bus brand
 * @param name The name of the bus brand
 */
public record BusBrandResource(
        Long id,
        String name
) { }
