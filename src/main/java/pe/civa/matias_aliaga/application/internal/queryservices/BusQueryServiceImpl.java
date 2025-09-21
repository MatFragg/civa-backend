package pe.civa.matias_aliaga.application.internal.queryservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.civa.matias_aliaga.domain.model.entities.Bus;
import pe.civa.matias_aliaga.domain.model.queries.GetAllBusesQuery;
import pe.civa.matias_aliaga.domain.model.queries.GetBusByIdQuery;
import pe.civa.matias_aliaga.domain.services.BusQueryService;
import pe.civa.matias_aliaga.infrastructure.persistence.jpa.repositories.BusRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the BusQueryService interface.
 * Handles all bus-related query operations using the BusRepository.
 */
@Service
public class BusQueryServiceImpl implements BusQueryService {
    /** Repository for bus data access operations */
    private final BusRepository repository;

    /**
     * Constructor for BusQueryServiceImpl.
     * @param repository The bus repository for data access
     */
    @Autowired
    public BusQueryServiceImpl(BusRepository repository) {
        this.repository = repository;
    }

    /**
     * Handles the retrieval of a bus by its unique identifier.
     * @param query The query containing the bus ID
     * @return Optional containing the bus if found, empty otherwise
     */
    @Override
    public Optional<Bus> handle(GetBusByIdQuery query) {
        return this.repository.findById(query.id());
    }

    /**
     * Handles the retrieval of all buses in the system.
     * @param query The get all buses query
     * @return List of all buses
     */
    @Override
    public List<Bus> handle(GetAllBusesQuery query) {
        return this.repository.findAll();
    }

    /**
     * Handles the retrieval of buses with pagination support.
     * @param pageable The pagination parameters (page number, size, sorting)
     * @return Page containing buses matching the pagination criteria
     */
    @Override
    public Page<Bus> handle(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
