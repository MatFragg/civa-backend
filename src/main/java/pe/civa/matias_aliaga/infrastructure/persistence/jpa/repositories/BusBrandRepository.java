package pe.civa.matias_aliaga.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.civa.matias_aliaga.domain.model.entities.BusBrand;
import pe.civa.matias_aliaga.domain.model.valueobjects.BusBrands;

import java.util.Optional;

/**
 * Repository interface for BusBrand entity data access operations.
 * Extends JpaRepository to provide standard CRUD operations and custom query methods.
 */
@Repository
public interface BusBrandRepository extends JpaRepository<BusBrand, Long> {
    /**
     * Checks if a bus brand exists by its unique identifier.
     * @param id The bus brand ID to check
     * @return true if the bus brand exists, false otherwise
     */
    boolean existsBusBrandById(Long id);

    /**
     * Checks if a bus brand exists by its name.
     * @param name The bus brand name to check
     * @return true if the bus brand exists, false otherwise
     */
    boolean existsBusBrandByName(BusBrands name);

    /**
     * Finds a bus brand by its name.
     * @param name The bus brand name to search for
     * @return Optional containing the bus brand if found, empty otherwise
     */
    Optional<BusBrand> findByName(BusBrands name);
}
