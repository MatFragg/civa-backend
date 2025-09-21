package pe.civa.matias_aliaga.application.internal.commandservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.civa.matias_aliaga.domain.model.commands.SeedBusBrandsCommand;
import pe.civa.matias_aliaga.domain.model.entities.BusBrand;
import pe.civa.matias_aliaga.domain.model.valueobjects.BusBrands;
import pe.civa.matias_aliaga.infrastructure.persistence.jpa.repositories.BusBrandRepository;

import java.util.Arrays;

/**
 * Implementation of the BusBrandCommandService interface.
 * Handles command operations for bus brands, including seeding of initial data.
 */
@Service
public class BusBrandCommandServiceImpl implements pe.civa.matias_aliaga.domain.services.BusBrandCommandService {

    /** Repository for bus brand data access operations */
    private final BusBrandRepository repository;

    /**
     * Constructor for BusBrandCommandServiceImpl.
     * @param repository The bus brand repository for data access
     */
    @Autowired
    public BusBrandCommandServiceImpl(BusBrandRepository repository) {
        this.repository = repository;
    }

    /**
     * Handles the seeding of all bus brands into the database.
     * Iterates through all available bus brand enums and creates database entries
     * for those that don't already exist.
     * @param command The seed bus brands command
     */
    @Override
    public void handle(SeedBusBrandsCommand command) {
        Arrays.stream(BusBrands.values())
                .forEach(busBrand -> {
                    if (!repository.existsBusBrandByName(busBrand)) {
                        repository.save(new BusBrand(BusBrands.valueOf(busBrand.name())));
                    }
                });
    }
}
