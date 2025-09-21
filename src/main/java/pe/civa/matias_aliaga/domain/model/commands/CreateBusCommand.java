package pe.civa.matias_aliaga.domain.model.commands;

import pe.civa.matias_aliaga.domain.model.valueobjects.BusBrands;

/**
 * Command to create a new bus.
 * @param busNumber Bus number.
 * @param licensePlate Bus license plate.
 * @param characteristics Bus characteristics.
 * @param brand Bus brand.
 * @param isActive Bus active status.
 */
public record CreateBusCommand(int busNumber, String licensePlate, String characteristics, String brand, boolean isActive) {
}
