package pe.civa.matias_aliaga.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.ToString;
import pe.civa.matias_aliaga.domain.model.commands.CreateBusCommand;
import pe.civa.matias_aliaga.domain.model.valueobjects.LicensePlate;
import pe.civa.matias_aliaga.shared.domain.model.entities.AuditableEntity;

/**
 * Bus entity representing a transportation vehicle.
 * Contains bus information including number, license plate, characteristics, brand and active status.
 */
@Getter
@Entity
@ToString
@Table(name="buses")
public class Bus extends AuditableEntity {

    /**
     * Bus number with validation constraints (1000-9999).
     */
    @Min(1000)
    @Max(9999)
    @Column(unique = true, nullable = false,name="bus_number",length = 4)
    private int busNumber;

    /**
     * Bus license plate as an embedded value object.
     */
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "license_plate", unique = true, nullable = false, length = 8))
    private LicensePlate licensePlate;

    /**
     * Bus characteristics description.
     */
    @Column(name="characteristics",length = 100)
    private String characteristics;

    /**
     * Bus brand reference.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id", nullable = false)
    private BusBrand brand;

    /**
     * Bus active status indicator.
     */
    @Column(name="is_active", nullable = false)
    private boolean isActive = true;

    /**
     * Default constructor for JPA.
     */
    public Bus() {
    }

    /**
     * Constructor to create a bus with all required parameters.
     * @param busNumber The bus number (must be positive)
     * @param licensePlate The bus license plate (cannot be null)
     * @param characteristics The bus characteristics description
     * @param isActive The bus active status
     * @param brand The bus brand (cannot be null)
     * @throws IllegalArgumentException if any validation fails
     */
    public Bus(int busNumber, LicensePlate licensePlate, String characteristics, boolean isActive, BusBrand brand) {
        if (busNumber <= 0) throw new IllegalArgumentException("Bus number must be positive.");
        if (licensePlate == null) throw new IllegalArgumentException("License plate is required.");
        if (brand == null) throw new IllegalArgumentException("Brand is required.");

        this.busNumber = busNumber;
        this.licensePlate = licensePlate;
        this.characteristics = characteristics;
        this.isActive = isActive;
        this.brand = brand;
    }

    /**
     * Constructor to create a bus from a CreateBusCommand and BusBrand.
     * @param command The create bus command containing bus data
     * @param busBrand The bus brand entity
     */
    public Bus(CreateBusCommand command, BusBrand busBrand) {
        this(
                command.busNumber(),
                new LicensePlate(command.licensePlate()),
                command.characteristics(),
                command.isActive(),
                busBrand
        );
    }

}
