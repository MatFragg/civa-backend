package pe.civa.matias_aliaga.domain.model.queries;

import pe.civa.matias_aliaga.domain.model.valueobjects.BusBrands;

/**
 * Query to get a bus brand by its name.
 * @param name Name of the bus brand.
 */
public record GetBusBrandByNameQuery(BusBrands name) {
}
