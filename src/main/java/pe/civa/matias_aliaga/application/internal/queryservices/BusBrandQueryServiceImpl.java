package pe.civa.matias_aliaga.application.internal.queryservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.civa.matias_aliaga.domain.model.entities.BusBrand;
import pe.civa.matias_aliaga.domain.model.queries.GetAllBusBrandsQuery;
import pe.civa.matias_aliaga.domain.model.queries.GetBusBrandByNameQuery;
import pe.civa.matias_aliaga.domain.services.BusBrandQueryService;
import pe.civa.matias_aliaga.infrastructure.persistence.jpa.repositories.BusBrandRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the BusBrandQueryService interface.
 * Handles all bus brand-related query operations using the BusBrandRepository.
 */
@Service
public class BusBrandQueryServiceImpl implements BusBrandQueryService {

    /** Repository for bus brand data access operations */
    private final BusBrandRepository repository;

    /**
     * Constructor for BusBrandQueryServiceImpl.
     * @param repository The bus brand repository for data access
     */
    @Autowired
    public BusBrandQueryServiceImpl(BusBrandRepository repository) {
        this.repository = repository;
    }

    /**
     * Handles the retrieval of all bus brands in the system.
     * @param query The get all bus brands query
     * @return List of all bus brands
     */
    @Override
    public List<BusBrand> handle(GetAllBusBrandsQuery query) {
        return this.repository.findAll();
    }

    /**
     * Handles the retrieval of a bus brand by its name.
     * @param query The query containing the bus brand name
     * @return Optional containing the bus brand if found, empty otherwise
     */
    @Override
    public Optional<BusBrand> handle(GetBusBrandByNameQuery query) {
        return this.repository.findByName(query.name());
    }
}
