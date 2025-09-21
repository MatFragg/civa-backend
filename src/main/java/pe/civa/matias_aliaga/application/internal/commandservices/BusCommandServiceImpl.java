package pe.civa.matias_aliaga.application.internal.commandservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.civa.matias_aliaga.domain.model.commands.CreateBusCommand;
import pe.civa.matias_aliaga.domain.model.entities.Bus;
import pe.civa.matias_aliaga.domain.model.valueobjects.BusBrands;
import pe.civa.matias_aliaga.domain.model.valueobjects.LicensePlate;
import pe.civa.matias_aliaga.domain.services.BusCommandService;
import pe.civa.matias_aliaga.infrastructure.persistence.jpa.repositories.BusBrandRepository;
import pe.civa.matias_aliaga.infrastructure.persistence.jpa.repositories.BusRepository;

/**
 * Implementation of the BusCommandService interface.
 * Handles command operations for buses, including creation and validation.
 */
@Service
public class BusCommandServiceImpl implements BusCommandService {
    /** Repository for bus data access operations */
    private final BusRepository busRepository;
    /** Repository for bus brand data access operations */
    private final BusBrandRepository busBrandRepository;

    /**
     * Constructor for BusCommandServiceImpl.
     * @param busRepository The bus repository for data access
     * @param busBrandRepository The bus brand repository for data access
     */
    @Autowired
    public BusCommandServiceImpl(BusRepository busRepository, BusBrandRepository busBrandRepository) {
        this.busRepository = busRepository;
        this.busBrandRepository = busBrandRepository;
    }

    /**
     * Handles the creation of a new bus.
     * Validates that the license plate and bus number combination doesn't already exist,
     * verifies that the bus brand exists, and creates a new bus entity.
     * @param command The create bus command containing bus details
     * @return The ID of the newly created bus
     * @throws IllegalArgumentException if bus already exists or brand doesn't exist
     */
    @Override
    public Long handle(CreateBusCommand command) {
        var licensePlate = new LicensePlate(command.licensePlate());
        if (this.busRepository.existsBusByLicensePlateAndBusNumber(licensePlate, command.busNumber())) {
            throw new IllegalArgumentException("Bus with license plate " + command.licensePlate() + " and bus number " + command.busNumber() + " already exists.");
        }

        var busBrand = this.busBrandRepository.findByName(BusBrands.fromString(command.brand()))
                .orElseThrow(() -> new IllegalArgumentException("Bus brand " + command.brand() + " does not exist."));

        var bus = new Bus(command, busBrand);
        busRepository.save(bus);

        return bus.getId();
    }
}
