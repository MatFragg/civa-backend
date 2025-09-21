package pe.civa.matias_aliaga.domain.services;

import pe.civa.matias_aliaga.domain.model.commands.SeedBusBrandsCommand;

/**
 * Service interface for handling bus brand commands.
 * Provides operations for managing bus brand data.
 */
public interface BusBrandCommandService {
    /**
     * Handles the seeding of bus brands into the system.
     * @param command The seed bus brands command
     */
    void handle(SeedBusBrandsCommand command);

}
