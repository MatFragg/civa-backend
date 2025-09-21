package pe.civa.matias_aliaga.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

/**
 * Value object representing a bus license plate.
 * Enforces the format validation pattern A1A-123 where A is a letter and 1 is a digit.
 */
@Getter
@Embeddable
public class LicensePlate {
    /** Validation pattern for license plate format: Letter-Digit-Letter-Hyphen-ThreeDigits */
    public static final String VALIDATION_PATTERN = "^[A-Z]\\d[A-Z]-\\d{3}$";

    /** The license plate value */
    private String value;

    /**
     * Constructor to create a license plate with validation.
     * @param value The license plate string to validate and store
     * @throws IllegalArgumentException if the value is null or doesn't match the required format
     */
    public LicensePlate(String value) {
        if (value == null || !value.matches(VALIDATION_PATTERN)) {
            throw new IllegalArgumentException("Invalid license plate format");
        }
        this.value = value;
    }

    /**
     * Default constructor for JPA.
     */
    public LicensePlate() {

    }

}