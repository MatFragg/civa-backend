package pe.civa.matias_aliaga.domain.services;

import pe.civa.matias_aliaga.domain.model.entities.BusBrand;
import pe.civa.matias_aliaga.domain.model.queries.GetAllBusBrandsQuery;
import pe.civa.matias_aliaga.domain.model.queries.GetBusBrandByNameQuery;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for handling bus brand queries.
 * Provides operations for retrieving bus brand data.
 */
public interface BusBrandQueryService {
    /**
     * Handles the retrieval of all bus brands.
     * @param query The get all bus brands query
     * @return List of all bus brands
     */
    List<BusBrand> handle(GetAllBusBrandsQuery query);

    /**
     * Handles the retrieval of a bus brand by its name.
     * @param query The get bus brand by name query
     * @return Optional containing the bus brand if found, empty otherwise
     */
    Optional<BusBrand> handle(GetBusBrandByNameQuery query);
}
