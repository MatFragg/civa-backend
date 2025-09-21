package pe.civa.matias_aliaga.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.civa.matias_aliaga.domain.model.entities.Bus;
import pe.civa.matias_aliaga.domain.model.valueobjects.LicensePlate;

/**
 * Repository interface for Bus entity data access operations.
 * Extends JpaRepository to provide standard CRUD operations and custom query methods.
 */
@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    /**
     * Checks if a bus exists by its unique identifier.
     * @param id The bus ID to check
     * @return true if the bus exists, false otherwise
     */
    boolean existsBusById(Long id);

    /**
     * Checks if a bus exists with the given license plate and bus number combination.
     * @param licensePlate The license plate to check
     * @param busNumber The bus number to check
     * @return true if a bus with this combination exists, false otherwise
     */
    boolean existsBusByLicensePlateAndBusNumber(LicensePlate licensePlate, int busNumber);
}
